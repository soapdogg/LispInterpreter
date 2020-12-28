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
    private val postEvaluationStackUpdater: PostEvaluationStackUpdater,
    private val condProgramStackItemEvaluator: CondProgramStackItemEvaluator,
    private val quoteFunctionEvaluator: QuoteFunctionEvaluator,
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

            if (top.functionName == FunctionNameConstants.COND) {
                programStack.push(top)
                programStack = condProgramStackItemEvaluator.evaluateCondProgramStackItem(
                    top,
                    programStack
                )
                continue
            }

            if (
                top.functionName == FunctionNameConstants.CONDCHILD
            ) {
                val condChildExprNode = top.functionExpressionNode

                programStack.push(top)
                if (top.currentParameterIndex == 1) {

                    val condChildsCondition = condChildExprNode.children[top.currentParameterIndex ]
                    if (condChildsCondition is ExpressionListNode) {
                        val condChildsConditionProgramStackItem = programStackItemGenerator.generateProgramStackItem(
                            condChildsCondition,
                            0,
                            top.variableMap
                        )
                        programStack.push(
                            condChildsConditionProgramStackItem
                        )
                    } else {
                        val updatedStacks = postEvaluationStackUpdater.updateStacksAfterEvaluation(
                            condChildsCondition,
                            top.variableMap,
                            evalStack,
                            programStack
                        )
                        evalStack = updatedStacks.evalStack
                        programStack = updatedStacks.programStack
                    }
                }
                else {
                    programStack.pop()
                    val evaluatedCondChild = evalStack.pop() as AtomNode
                    if (evaluatedCondChild.value != ReservedValuesConstants.NIL) {
                        while (
                            programStack.peek().functionName == FunctionNameConstants.CONDCHILD
                        ) {
                            programStack.pop()
                        }
                        programStack.pop() //CondProgramStackItem


                        val condChildsValue = top.functionExpressionNode.children[top.currentParameterIndex ]
                        if (condChildsValue is ExpressionListNode) {
                            val condChildsValueProgramStackItem = programStackItemGenerator.generateProgramStackItem(
                                condChildsValue,
                                0,
                                top.variableMap
                            )
                            programStack.push(
                                condChildsValueProgramStackItem
                            )
                        } else {
                            val updatedStacks = postEvaluationStackUpdater.updateStacksAfterEvaluation(
                                condChildsValue,
                                top.variableMap,
                                evalStack,
                                programStack
                            )
                            evalStack = updatedStacks.evalStack
                            programStack = updatedStacks.programStack
                        }
                    }
                }
                continue
            }

            if (top.functionName == FunctionNameConstants.QUOTE) {
                val updatedStacks = quoteFunctionEvaluator.evaluateQuoteFunction(
                    top,
                    evalStack,
                    programStack
                )
                evalStack = updatedStacks.evalStack
                programStack = updatedStacks.programStack
                continue
            }

            val expectedFunctionLength = functionLengthDeterminer.determineFunctionLength(top.functionExpressionNode)
            if (top.currentParameterIndex < expectedFunctionLength) {
                val nthChild = top.functionExpressionNode.children[top.currentParameterIndex]
                programStack.push(top)
                if (nthChild is ExpressionListNode) {
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
                            top.variableMap,
                            evalStack,
                            programStack
                        )
                        evalStack = updatedStacks.evalStack
                        programStack = updatedStacks.programStack
                    }
                } else {
                    val updatedStacks = postEvaluationStackUpdater.updateStacksAfterEvaluation(
                        nthChild,
                        top.variableMap,
                        evalStack,
                        programStack
                    )
                    evalStack = updatedStacks.evalStack
                    programStack = updatedStacks.programStack
                }
            }
            else {
                val functionStack = Stack<NodeV2>()
                for (i in 0 until expectedFunctionLength) {
                    functionStack.push(evalStack.pop())
                }

                val functionNameNode = functionStack.pop()
                val functionName = top.functionName
                if (functionName == ReservedValuesConstants.NIL) {
                    val updatedStacks = postEvaluationStackUpdater.updateStacksAfterEvaluation(
                        functionNameNode,
                        top.variableMap,
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
                        val param = functionStack.pop()

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
                            top.variableMap,
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

        return evalStack[0]
    }
}