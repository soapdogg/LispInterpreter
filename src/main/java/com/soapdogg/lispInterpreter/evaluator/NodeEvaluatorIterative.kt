package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.determiner.FunctionLengthDeterminer
import com.soapdogg.lispInterpreter.function.Function
import java.util.*
import kotlin.collections.HashMap

class NodeEvaluatorIterative(
    private val topProgramStackItemCreator: TopProgramStackItemCreator,
    private val functionLengthDeterminer: FunctionLengthDeterminer,
    private val functionMap: Map<String, Function>,
    private val postEvaluationStackUpdater: PostEvaluationStackUpdater,
    private val condFunctionEvaluator: CondFunctionEvaluator,
    private val quoteFunctionEvaluator: QuoteFunctionEvaluator,
    private val builtInFunctionEvaluator: BuiltInFunctionEvaluator
){

    fun evaluate(
        expressionListNode: ExpressionListNode,
        userDefinedFunctions: Map<String, UserDefinedFunction>
    ): NodeV2 {

        val programStack = Stack<ProgramStackItem>()
        val evalStack = Stack<NodeV2>()

        topProgramStackItemCreator.createTopProgramStackItem(
            expressionListNode,
            emptyMap(),
            programStack
        )

        while(
            programStack.isNotEmpty()
        ) {
            val top = programStack.pop()

            if (top.functionName == FunctionNameConstants.COND) {
                programStack.push(top)
                condFunctionEvaluator.evaluateCondProgramStackItem(
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
                if (top.currentParameterIndex == 0) {

                    val condChildsCondition = condChildExprNode.children[top.currentParameterIndex +1]
                    if (condChildsCondition is ExpressionListNode) {
                        if (condChildsCondition.children.size > 1) {
                            topProgramStackItemCreator.createTopProgramStackItem(
                                condChildsCondition,
                                top.variableMap,
                                programStack
                            )
                        } else {
                            postEvaluationStackUpdater.updateStacksAfterEvaluation(
                                condChildsCondition.children[0],
                                top.variableMap,
                                evalStack,
                                programStack
                            )
                        }
                    } else {
                        postEvaluationStackUpdater.updateStacksAfterEvaluation(
                            condChildsCondition,
                            top.variableMap,
                            evalStack,
                            programStack
                        )
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


                        val condChildsValue = top.functionExpressionNode.children[top.currentParameterIndex +1]
                        if (condChildsValue is ExpressionListNode) {
                            if (condChildsValue.children.size > 1) {
                                topProgramStackItemCreator.createTopProgramStackItem(
                                    condChildsValue,
                                    top.variableMap,
                                    programStack
                                )
                            } else {
                                postEvaluationStackUpdater.updateStacksAfterEvaluation(
                                    condChildsValue.children[0],
                                    top.variableMap,
                                    evalStack,
                                    programStack
                                )
                            }
                        }
                        else {
                            postEvaluationStackUpdater.updateStacksAfterEvaluation(
                                condChildsValue,
                                top.variableMap,
                                evalStack,
                                programStack
                            )
                        }
                    }
                }
                continue
            }

            if (top.functionName == FunctionNameConstants.QUOTE) {
                quoteFunctionEvaluator.evaluateQuoteFunction(
                    top,
                    evalStack,
                    programStack
                )
                continue
            }

            val expectedFunctionLength = functionLengthDeterminer.determineFunctionLength(top.functionExpressionNode)
            if (top.currentParameterIndex < expectedFunctionLength) {
                val nthChild = top.functionExpressionNode.children[top.currentParameterIndex]
                programStack.push(top)
                if (nthChild is ExpressionListNode) {
                    if (nthChild.children.size > 1) {
                        topProgramStackItemCreator.createTopProgramStackItem(
                            nthChild,
                            top.variableMap,
                            programStack
                        )
                    } else {
                        postEvaluationStackUpdater.updateStacksAfterEvaluation(
                            nthChild.children[0],
                            top.variableMap,
                            evalStack,
                            programStack
                        )
                    }
                } else {
                    postEvaluationStackUpdater.updateStacksAfterEvaluation(
                        nthChild,
                        top.variableMap,
                        evalStack,
                        programStack
                    )
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
                    postEvaluationStackUpdater.updateStacksAfterEvaluation(
                        functionNameNode,
                        top.variableMap,
                        evalStack,
                        programStack
                    )
                }
                else if (functionMap.containsKey(functionName)) {
                    builtInFunctionEvaluator.evaluateBuiltInFunction(
                        functionName,
                        functionStack,
                        top,
                        evalStack,
                        programStack
                    )
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
                        topProgramStackItemCreator.createTopProgramStackItem(
                            userDefinedFunction.body,
                            mapCopy,
                            programStack
                        )
                    } else {
                        postEvaluationStackUpdater.updateStacksAfterEvaluation(
                            userDefinedFunction.body,
                            top.variableMap,
                            evalStack,
                            programStack
                        )
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