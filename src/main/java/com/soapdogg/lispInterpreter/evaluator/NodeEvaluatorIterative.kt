package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.determiner.FunctionLengthDeterminer
import com.soapdogg.lispInterpreter.function.Function
import com.soapdogg.lispInterpreter.generator.ProgramStackItemGenerator
import java.util.*
import kotlin.collections.HashMap

class NodeEvaluatorIterative(
    private val programStackItemGenerator: ProgramStackItemGenerator,
    private val functionLengthDeterminer: FunctionLengthDeterminer,
    private val functionMap: Map<String, Function>,
    private val topProgramStackItemUpdater: TopProgramStackItemUpdater,
    private val postEvaluationStackUpdater: PostEvaluationStackUpdater,
    private val condProgramStackItemEvaluator: CondProgramStackItemEvaluator,
    private val builtInFunctionEvaluator: BuiltInFunctionEvaluator
){

    fun evaluate(
        expressionListNode: ExpressionListNode,
        userDefinedFunctions: Map<String, UserDefinedFunction>
    ): NodeV2 {

        var programStack = Stack<ProgramStackItem>()
        var evalStack = Stack<NodeV2>()

        val root = programStackItemGenerator.generateProgramStackItem(
            expressionListNode,
            0,
            mapOf()
        )
        programStack.push(
            root
        )

        while(
            programStack.isNotEmpty()
        ) {
            val top = programStack.pop()

            if (
                top.functionExpressionNode.children[0] is AtomNode
                && (
                    ((top.functionExpressionNode.children[0] as AtomNode).value == FunctionNameConstants.COND)
                    ||
                    ((top.functionExpressionNode.children[0] as AtomNode).value == FunctionNameConstants.CONDCHILD)
                    )
            ) {
                val firstChild = top.functionExpressionNode.children[0] as AtomNode
                programStack = condProgramStackItemEvaluator.evaluateCondProgramStackItem(
                    firstChild.value,
                    top,
                    programStack
                )

                if (firstChild.value == FunctionNameConstants.CONDCHILD) {
                    val condChildExprNode = top.functionExpressionNode
                    val secondChild = condChildExprNode.children[1] as ExpressionListNode

                    programStack.push(top)
                    if (top.currentParameterIndex == 0) {

                        programStack = topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
                            programStack
                        )
                        val secondChildsFirstChild = secondChild.children[0]
                        if (secondChildsFirstChild is ExpressionListNode) {
                            val secondChildsFirstChildProgramStackItem = programStackItemGenerator.generateProgramStackItem(
                                secondChildsFirstChild,
                                0,
                                top.variableMap
                            )
                            programStack.push(
                                secondChildsFirstChildProgramStackItem
                            )
                        } else {
                            val secondChildAtomNode = secondChildsFirstChild as AtomNode
                            val pusher = top.variableMap.getOrDefault(secondChildAtomNode.value, secondChildAtomNode)
                            evalStack.push(pusher)
                        }
                    }
                    else {
                        programStack.pop()
                        val evaluatedCondChild = evalStack.pop() as AtomNode
                        if (evaluatedCondChild.value != ReservedValuesConstants.NIL) {
                            while (
                                (programStack.peek().functionExpressionNode.children[0] as AtomNode).value == FunctionNameConstants.CONDCHILD
                            ) {
                                programStack.pop()
                            }
                            val cond = programStack.pop()
                            if (secondChild.children[1] is ExpressionListNode) {
                                val secondChildsSecondChildProgramStackItem = programStackItemGenerator.generateProgramStackItem(
                                    secondChild.children[1] as ExpressionListNode,
                                    0,
                                    cond.variableMap
                                )
                                programStack.push(
                                    secondChildsSecondChildProgramStackItem
                                )
                            } else {
                                val secondChildAtomNode = secondChild.children[1] as AtomNode
                                val pusher = cond.variableMap.getOrDefault(secondChildAtomNode.value, secondChildAtomNode)
                                evalStack.push(pusher)
                                programStack = topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
                                    programStack
                                )
                            }
                        }
                    }
                }
                continue
            }

            val nthChild = top.functionExpressionNode.children[top.currentParameterIndex]
            if (nthChild is ExpressionListNode) {
                programStack.push(top)
                if (nthChild.children.size > 1) {
                    val nthChildProgramStackItem = programStackItemGenerator.generateProgramStackItem(
                        nthChild,
                        0,
                        top.variableMap
                    )
                    programStack.push(
                        nthChildProgramStackItem
                    )
                } else {
                    val updatedStacks = postEvaluationStackUpdater.updateStacksAfterEvaluation(
                        nthChild.children[0],
                        evalStack,
                        programStack
                    )
                    evalStack = updatedStacks.evalStack
                    programStack = updatedStacks.programStack
                }
            }
            else {
                val nthChildAtomNode = nthChild as AtomNode
                if (nthChildAtomNode.value == FunctionNameConstants.QUOTE) {
                    val quoteExprNode = top.functionExpressionNode
                    val secondChild = quoteExprNode.children[1]
                    val updatedStacks = postEvaluationStackUpdater.updateStacksAfterEvaluation(
                        secondChild,
                        evalStack,
                        programStack
                    )
                    evalStack = updatedStacks.evalStack
                    programStack = updatedStacks.programStack
                    continue
                }

                val expectedFunctionLength = functionLengthDeterminer.determineFunctionLength(top.functionExpressionNode)
                if (top.currentParameterIndex < expectedFunctionLength) {
                    programStack.push(top)
                    val updatedStacks = postEvaluationStackUpdater.updateStacksAfterEvaluation(
                        nthChildAtomNode,
                        evalStack,
                        programStack
                    )
                    evalStack = updatedStacks.evalStack
                    programStack = updatedStacks.programStack
                }
                else {
                    val functionStack = Stack<NodeV2>()
                    for (i in 0 until expectedFunctionLength) {
                        functionStack.push(evalStack.pop())
                    }

                    val functionNameNode = functionStack.pop()
                    val functionName = (functionNameNode as AtomNode).value
                    if (functionName == ReservedValuesConstants.NIL) {
                        val updatedStacks = postEvaluationStackUpdater.updateStacksAfterEvaluation(
                            functionNameNode,
                            evalStack,
                            programStack
                        )
                        evalStack = updatedStacks.evalStack
                        programStack = updatedStacks.programStack
                    }
                    else if (functionMap.containsKey(functionName)) {
                        val updatedStacks = builtInFunctionEvaluator.evaluateBuiltInFunction(
                            functionName,
                            functionStack,
                            top,
                            evalStack,
                            programStack
                        )
                        evalStack = updatedStacks.evalStack
                        programStack = updatedStacks.programStack
                    }
                    else if (userDefinedFunctions.containsKey(functionName)) {
                        val userDefinedFunction = userDefinedFunctions.getValue(functionName)
                        var i = 0
                        val mapCopy = HashMap(top.variableMap)
                        while (functionStack.isNotEmpty()) {
                            var param = functionStack.pop()
                            if (param is AtomNode) {
                                param = top.variableMap.getOrDefault(param.value, param)
                            }
                            val variableName = userDefinedFunction.formalParameters[i]
                            mapCopy[variableName] = param
                            ++i
                        }
                        if (userDefinedFunction.body is ExpressionListNode && userDefinedFunction.formalParameters.isNotEmpty()) {
                            val userDefinedFunctionBodyProgramStackItem = programStackItemGenerator.generateProgramStackItem(
                                userDefinedFunction.body,
                                0,
                                mapCopy
                            )
                            programStack.push(
                                userDefinedFunctionBodyProgramStackItem
                            )
                        } else {
                            val updatedStacks = postEvaluationStackUpdater.updateStacksAfterEvaluation(
                                userDefinedFunction.body,
                                evalStack,
                                programStack
                            )
                            evalStack = updatedStacks.evalStack
                            programStack = updatedStacks.programStack
                        }
                    }
                    else {
                        throw Exception("Error! Invalid CAR value: $functionName\n")
                    }
                }
            }
        }

        return evalStack[0]
    }
}