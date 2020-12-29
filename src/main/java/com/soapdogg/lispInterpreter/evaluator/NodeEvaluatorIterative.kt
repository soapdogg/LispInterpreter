package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.*
import java.util.*

class NodeEvaluatorIterative(
    private val topProgramStackItemCreator: TopProgramStackItemCreator,
    private val condFunctionEvaluator: CondFunctionEvaluator,
    private val condChildFunctionEvaluator: CondChildFunctionEvaluator,
    private val quoteFunctionEvaluator: QuoteFunctionEvaluator,
    private val unfinishedProgramStackItemEvaluator: UnfinishedProgramStackItemEvaluator,
    private val finishedProgramStackItemEvaluator: FinishedProgramStackItemEvaluator
){

    fun evaluate(
        rootNode: ExpressionListNode,
        userDefinedFunctions: Map<String, UserDefinedFunction>
    ): NodeV2 {

        val programStack = Stack<ProgramStackItem>()
        val evalStack = Stack<NodeV2>()

        topProgramStackItemCreator.createTopProgramStackItem(
            rootNode,
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
                unfinishedProgramStackItemEvaluator.evaluateUnfinishedProgramStackItem(
                    top,
                    evalStack,
                    programStack
                )
            }
            else {
                finishedProgramStackItemEvaluator.evaluateFinishedProgramStackItem(
                    top,
                    userDefinedFunctions,
                    evalStack,
                    programStack
                )
            }
        }

        return evalStack[0]
    }
}