package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.function.Function
import java.util.*

class NodeEvaluatorIterative(
    private val topProgramStackItemCreator: TopProgramStackItemCreator,
    private val stackUpdateDeterminer: StackUpdateDeterminer,
    private val functionMap: Map<String, Function>,
    private val postEvaluationStackUpdater: PostEvaluationStackUpdater,
    private val condFunctionEvaluator: CondFunctionEvaluator,
    private val condChildFunctionEvaluator: CondChildFunctionEvaluator,
    private val quoteFunctionEvaluator: QuoteFunctionEvaluator,
    private val builtInFunctionEvaluator: BuiltInFunctionEvaluator,
    private val userDefinedFunctionEvaluator: UserDefinedFunctionEvaluator
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
                condFunctionEvaluator.evaluateCondProgramStackItem(
                    top,
                    programStack
                )
            }

            else if (top.functionName == FunctionNameConstants.CONDCHILD) {
                condChildFunctionEvaluator.evaluateCondChildFunction(
                    top,
                    evalStack,
                    programStack
                )
            }

            else if (top.functionName == FunctionNameConstants.QUOTE) {
                quoteFunctionEvaluator.evaluateQuoteFunction(
                    top,
                    evalStack,
                    programStack
                )
            }

            else if (top.currentParameterIndex < top.functionExpressionNode.children.size - 1) {
                val nthChild = top.functionExpressionNode.children[top.currentParameterIndex]
                programStack.push(top)
                stackUpdateDeterminer.determineHowToUpdateStacks(
                    nthChild,
                    top.variableMap,
                    evalStack,
                    programStack
                )
            }
            else {
                val functionStack = Stack<NodeV2>()
                for (i in 0 until top.functionExpressionNode.children.size - 1) {
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
                    userDefinedFunctionEvaluator.evaluateUserDefinedFunction(
                        userDefinedFunctions.getValue(functionName),
                        top.variableMap,
                        functionStack,
                        evalStack,
                        programStack
                    )
                }
                else {
                    throw Exception("Error! Invalid CAR value: $functionName\n")
                }
            }
        }

        return evalStack[0]
    }
}