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
                        val condRootStackItem = programStackItemGenerator.generateProgramStackItem(
                            top.functionExpressionNode,
                            top.currentParameterIndex + 1,
                            top.variableMap
                        )
                        programStack.push(
                            condRootStackItem
                        )
                        for (i in top.functionExpressionNode.children.size - 2 downTo 1) {
                            val condChildStackItem = programStackItemGenerator.generateProgramStackItem(
                                ExpressionListNode(
                                    listOf(
                                        AtomNode(FunctionNameConstants.CONDCHILD), top.functionExpressionNode.children[i],

                                    )
                                ),
                                0,
                                top.variableMap
                            )
                            programStack.push(
                                condChildStackItem
                            )
                        }
                        continue
                    } else if (top.currentParameterIndex == 2) {
                        programStack = topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
                            programStack
                        )
                        continue
                    } else {
                        throw NotAListException("Error! None of the conditions in the COND function evaluated to true.\n")
                    }
                }

                if (firstChild.value == FunctionNameConstants.CONDCHILD) {
                    val condChildExprNode = top.functionExpressionNode
                    val secondChild = condChildExprNode.children[1] as ExpressionListNode

                    if (top.currentParameterIndex == 0) {
                        val condChildConditionProgramStackItem = programStackItemGenerator.generateProgramStackItem(
                            top.functionExpressionNode,
                            1,
                            top.variableMap
                        )
                        programStack.push(
                            condChildConditionProgramStackItem
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
                            evalStack.push(secondChild.children[0])
                        }
                    } else {
                        if (evalStack.isNotEmpty()) {
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
                                val updatedCondProgramStackItem = programStackItemGenerator.generateProgramStackItem(
                                    cond.functionExpressionNode,
                                    2,
                                    cond.variableMap
                                )
                                programStack.push(
                                    updatedCondProgramStackItem
                                )
                            }
                        }
                    }
                    continue
                }
            }

            val nthChild = top.functionExpressionNode.children[top.currentParameterIndex]
            if (nthChild is ExpressionListNode && nthChild.children.size > 1) {
                programStack.push(top)
                programStack.push(
                    ProgramStackItem(
                        nthChild,
                        0,
                        top.variableMap
                    )
                )
                continue
            } else if (nthChild is ExpressionListNode ) {
                evalStack.push(nthChild.children[0])
                val updatedHead = programStackItemGenerator.generateProgramStackItem(
                    top.functionExpressionNode,
                    top.currentParameterIndex + 1,
                    top.variableMap
                )
                programStack.push(
                   updatedHead
                )
                continue
            }

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
            if (top.currentParameterIndex < expectedFunctionLength) {
                evalStack.push(nthChildAtomNode)
                programStack.push(
                    ProgramStackItem(
                        top.functionExpressionNode,
                        top.currentParameterIndex + 1,
                        top.variableMap
                    )
                )
            } else {
                programStack.push(top)
            }

            val evaluatingTop = programStack.peek()
            if (expectedFunctionLength == evaluatingTop.currentParameterIndex) {
                val functionStack = Stack<NodeV2>()
                for (i in 0 until expectedFunctionLength) {
                    functionStack.push(evalStack.pop())
                }

                val functionNameNode = functionStack.pop()
                val functionName = (functionNameNode as AtomNode).value
                if (functionName == ReservedValuesConstants.NIL) {
                    evalStack.push(functionNameNode)
                    programStack.pop() // remove ExpressionNode from program stack since we have evaluated it
                } else if (functionMap.containsKey(functionName)) {
                    val function = functionMap.getValue(functionName)
                    val evaluatedFunctionResult = function.evaluate(functionStack, evaluatingTop.variableMap)
                    evalStack.push(evaluatedFunctionResult)
                    programStack.pop() // remove ExpressionNode from program stack since we have evaluated it
                } else if (userDefinedFunctions.containsKey(functionName)) {
                    val userDefinedFunction = userDefinedFunctions.getValue(functionName)
                    var i = 0
                    val mapCopy = HashMap(evaluatingTop.variableMap)
                    while(functionStack.isNotEmpty()) {
                        var param = functionStack.pop()
                        if (param is AtomNode) {
                            param = evaluatingTop.variableMap.getOrDefault(param.value, param)
                        }
                        val variableName = userDefinedFunction.formalParameters[i]
                        mapCopy[variableName] = param
                        ++i
                    }
                    programStack.pop() // remove ExpressionNode from program stack since we have evaluated it
                    if (userDefinedFunction.body is ExpressionListNode && userDefinedFunction.formalParameters.isNotEmpty()) {
                        programStack.push(
                            ProgramStackItem(
                                userDefinedFunction.body,
                                0,
                                mapCopy
                            )
                        )
                    } else {
                        evalStack.push(userDefinedFunction.body)
                        programStack = topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
                            programStack
                        )
                    }
                    continue
                } else {
                    throw Exception("Error! Invalid CAR value: $functionName\n")
                }

                programStack = topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
                    programStack
                )
            }
        }

        return evalStack[0]
    }
}