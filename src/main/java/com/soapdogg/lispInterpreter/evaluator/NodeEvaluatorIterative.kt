package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.determiner.FunctionLengthDeterminer
import com.soapdogg.lispInterpreter.exceptions.NotAListException
import com.soapdogg.lispInterpreter.function.Function
import java.util.*

class NodeEvaluatorIterative(
    private val functionLengthDeterminer: FunctionLengthDeterminer,
    private val functionMap: Map<String, Function>
){

    fun evaluate(
        expressionListNode: ExpressionListNode,
        userDefinedFunctions: Map<String, UserDefinedFunction>
    ): NodeV2 {

        val programStack = Stack<StackItem>()
        val evalStack = Stack<NodeV2>()

        programStack.push(StackItem(expressionListNode, 0, mutableMapOf()))

        while(
            programStack.isNotEmpty()
        ) {
            val top = programStack.pop()

            if (top.expressionListNode.children[0] is AtomNode) {
                val firstChild = top.expressionListNode.children[0] as AtomNode
                if (firstChild.value == FunctionNameConstants.COND) {
                    if (top.currentChildIndex == 0) {
                        programStack.push(
                            StackItem(
                                top.expressionListNode,
                                top.currentChildIndex + 1,
                                top.variableMap
                            )
                        )
                        for (i in top.expressionListNode.children.size - 2 downTo 1) {
                            programStack.push(
                                StackItem(
                                    ExpressionListNode(
                                        listOf(
                                            AtomNode(FunctionNameConstants.CONDCHILD), top.expressionListNode.children[i]
                                        )
                                    ),
                                    0,
                                    top.variableMap
                                )
                            )
                        }
                        continue
                    } else if (top.currentChildIndex == 2) {
                        if (programStack.isNotEmpty()) {
                            val head = programStack.pop()
                            programStack.push(
                                StackItem(
                                    head.expressionListNode,
                                    head.currentChildIndex + 1,
                                    head.variableMap
                                )
                            )
                        }
                        continue
                    } else {
                        throw NotAListException("Error! None of the conditions in the COND function evaluated to true.\n")
                    }
                }

                if (firstChild.value == FunctionNameConstants.CONDCHILD) {
                    val condChildExprNode = top.expressionListNode
                    val secondChild = condChildExprNode.children[1] as ExpressionListNode

                    if (top.currentChildIndex == 0) {
                        programStack.push(
                            StackItem(
                                top.expressionListNode,
                                1,
                                top.variableMap
                            )
                        )
                        val secondChildsFirstChild = secondChild.children[0]
                        if (secondChildsFirstChild is ExpressionListNode) {
                            programStack.push(
                                StackItem(
                                    secondChildsFirstChild,
                                    0,
                                    top.variableMap
                                )
                            )
                        } else {
                            evalStack.push(secondChild.children[0])
                        }
                    } else {
                        if (evalStack.isNotEmpty()) {
                            val evaluatedCondChild = evalStack.pop() as AtomNode
                            if (evaluatedCondChild.value != ReservedValuesConstants.NIL) {
                                evalStack.push(secondChild.children[1])
                                while (
                                    (programStack.peek().expressionListNode.children[0] as AtomNode).value == FunctionNameConstants.CONDCHILD
                                ) {
                                    programStack.pop()
                                }
                                val cond = programStack.pop()
                                programStack.push(
                                    StackItem(
                                        cond.expressionListNode,
                                        2,
                                        cond.variableMap
                                    )
                                )
                            }
                        }
                    }
                    continue
                }
            }

            val nthChild = top.expressionListNode.children[top.currentChildIndex]
            if (nthChild is ExpressionListNode && nthChild.children.size > 1) {
                programStack.push(top)
                programStack.push(
                    StackItem(
                        nthChild,
                        0,
                        top.variableMap
                    )
                )
                continue
            } else if (nthChild is ExpressionListNode ) {
                evalStack.push(nthChild.children[0])
                programStack.push(
                    StackItem(
                        top.expressionListNode,
                        top.currentChildIndex + 1,
                        top.variableMap
                    )
                )
                continue
            }

            val nthChildAtomNode = nthChild as AtomNode
            if (nthChildAtomNode.value == FunctionNameConstants.QUOTE) {
                val quoteExprNode = top.expressionListNode
                val secondChild = quoteExprNode.children[1]
                evalStack.push(secondChild)
                if (programStack.isNotEmpty()) {
                    val head = programStack.pop()
                    programStack.push(
                        StackItem(
                            head.expressionListNode,
                            head.currentChildIndex + 1,
                            head.variableMap
                        )
                    )
                }
                continue
            }

            val expectedFunctionLength = functionLengthDeterminer.determineFunctionLength(top.expressionListNode)
            if (top.currentChildIndex < expectedFunctionLength) {
                evalStack.push(nthChildAtomNode)
                programStack.push(
                    StackItem(
                        top.expressionListNode,
                        top.currentChildIndex + 1,
                        top.variableMap
                    )
                )
            } else {
                programStack.push(top)
            }

            val evaluatingTop = programStack.peek()
            if (expectedFunctionLength == evaluatingTop.currentChildIndex) {
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
                    val mapCopy = evaluatingTop.variableMap
                    while(functionStack.isNotEmpty()) {
                        val param = functionStack.pop()
                        val variableName = userDefinedFunction.formalParameters[i]
                        mapCopy[variableName] = param
                        ++i
                    }
                    programStack.pop() // remove ExpressionNode from program stack since we have evaluated it
                    if (userDefinedFunction.body is ExpressionListNode) {
                        programStack.push(
                            StackItem(
                                userDefinedFunction.body,
                                0,
                                mapCopy
                            )
                        )
                    } else {
                        evalStack.push(userDefinedFunction.body)
                    }
                    continue
                } else {
                    throw Exception("Error! Invalid CAR value: $functionName\n")
                }


                if (programStack.isNotEmpty()) {
                    val head = programStack.pop()
                    programStack.push(
                        StackItem(head.expressionListNode,
                            head.currentChildIndex + 1,
                        head.variableMap
                        )
                    )
                }
            }
        }

        return evalStack[0]
    }
}