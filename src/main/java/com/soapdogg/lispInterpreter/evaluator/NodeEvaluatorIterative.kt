package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.determiner.FunctionLengthDeterminer
import com.soapdogg.lispInterpreter.exceptions.NotAListException
import com.soapdogg.lispInterpreter.function.Function
import com.soapdogg.lispInterpreter.generator.ProgramStackItemGenerator
import java.util.*
import kotlin.collections.HashMap

class NodeEvaluatorIterative(
    private val condChildStackItemBuilder: CondChildStackItemBuilder,
    private val programStackItemGenerator: ProgramStackItemGenerator,
    private val functionLengthDeterminer: FunctionLengthDeterminer,
    private val functionMap: Map<String, Function>,
    private val topProgramStackItemUpdater: TopProgramStackItemUpdater
){

    fun evaluate(
        expressionListNode: ExpressionListNode,
        userDefinedFunctions: Map<String, UserDefinedFunction>
    ): NodeV2 {

        var programStack = Stack<ProgramStackItem>()
        val evalStack = Stack<NodeV2>()

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

            if (top.functionExpressionNode.children[0] is AtomNode) {
                val firstChild = top.functionExpressionNode.children[0] as AtomNode
                if (firstChild.value == FunctionNameConstants.COND) {
                    if (top.currentParameterIndex == 0) {
                        programStack.push(top)
                        programStack = topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
                            programStack
                        )
                        programStack = condChildStackItemBuilder.buildCondChildStackItems(
                            top,
                            programStack
                        )
                    } else if (top.currentParameterIndex == 2) {
                        programStack = topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
                            programStack
                        )
                    } else {
                        throw NotAListException("Error! None of the conditions in the COND function evaluated to true.\n")
                    }
                    continue
                }

                if (firstChild.value == FunctionNameConstants.CONDCHILD) {
                    val condChildExprNode = top.functionExpressionNode
                    val secondChild = condChildExprNode.children[1] as ExpressionListNode

                    if (top.currentParameterIndex == 0) {
                        programStack.push(top)
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
                            evalStack.push(secondChildsFirstChild)
                        }
                    }
                    else if (evalStack.isNotEmpty()) {
                        val evaluatedCondChild = evalStack.pop() as AtomNode
                        if (evaluatedCondChild.value != ReservedValuesConstants.NIL) {
                            while (
                                (programStack.peek().functionExpressionNode.children[0] as AtomNode).value == FunctionNameConstants.CONDCHILD
                            ) {
                                programStack.pop()
                            }
                            val cond = programStack.peek()
                            if (secondChild.children[1] is ExpressionListNode) {
                                val secondChildsSecondChildProgramStackItem = programStackItemGenerator.generateProgramStackItem(
                                    secondChild.children[1] as ExpressionListNode,
                                    -1,
                                    cond.variableMap
                                )
                                programStack.push(
                                    secondChildsSecondChildProgramStackItem
                                )
                            } else {
                                val secondChildAtomNode = secondChild.children[1] as AtomNode
                                val pusher = cond.variableMap.getOrDefault(secondChildAtomNode.value, secondChildAtomNode)
                                evalStack.push(pusher)
                            }
                            programStack = topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
                                programStack
                            )
                        }
                    }
                    continue
                }
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
                    evalStack.push(nthChild.children[0])
                    programStack = topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
                        programStack
                    )
                }
            }
            else {
                val nthChildAtomNode = nthChild as AtomNode
                if (nthChildAtomNode.value == FunctionNameConstants.QUOTE) {
                    val quoteExprNode = top.functionExpressionNode
                    val secondChild = quoteExprNode.children[1]
                    evalStack.push(secondChild)
                    programStack = topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
                        programStack
                    )
                    continue
                }

                val expectedFunctionLength = functionLengthDeterminer.determineFunctionLength(top.functionExpressionNode)
                programStack.push(top)
                if (top.currentParameterIndex < expectedFunctionLength) {
                    programStack = topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
                        programStack
                    )
                    evalStack.push(nthChildAtomNode)
                }

                if (expectedFunctionLength == top.currentParameterIndex) {
                    programStack.pop()
                    val functionStack = Stack<NodeV2>()
                    for (i in 0 until expectedFunctionLength) {
                        functionStack.push(evalStack.pop())
                    }

                    val functionNameNode = functionStack.pop()
                    val functionName = (functionNameNode as AtomNode).value
                    if (functionName == ReservedValuesConstants.NIL) {
                        evalStack.push(functionNameNode)
                        programStack = topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
                            programStack
                        )
                    }
                    else if (functionMap.containsKey(functionName)) {
                        val function = functionMap.getValue(functionName)
                        val evaluatedFunctionResult = function.evaluate(functionStack, top.variableMap)
                        evalStack.push(evaluatedFunctionResult)
                        programStack = topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
                            programStack
                        )
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
                            evalStack.push(userDefinedFunction.body)
                            programStack = topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
                                programStack
                            )
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