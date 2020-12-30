package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.*
import java.util.Stack

class RootNodeEvaluator(
    private val topProgramStackItemCreator: TopProgramStackItemCreator,
    private val condFunctionEvaluator: CondFunctionEvaluator,
    private val condChildFunctionEvaluator: CondChildFunctionEvaluator,
    private val quoteFunctionEvaluator: QuoteFunctionEvaluator,
    private val unfinishedProgramStackItemEvaluator: UnfinishedProgramStackItemEvaluator,
    private val finishedProgramStackItemEvaluator: FinishedProgramStackItemEvaluator
){

    fun evaluate(
        rootNode: ExpressionListNode,
        userDefinedFunctions: Map<String, UserDefinedFunction>,
        programStack: MyStack<ProgramStackItem>,
        evalStack: Stack<NodeV2>
    ): NodeV2 {

        topProgramStackItemCreator.createTopProgramStackItem(
            rootNode,
            emptyMap(),
            programStack
        )

        while(
            programStack.isNotEmpty()
        ) {
            val top = programStack.pop()

            when {
                top.functionName == FunctionNameConstants.COND -> {
                    condFunctionEvaluator.evaluateCondProgramStackItem(
                        top,
                        programStack
                    )
                }
                top.functionName == FunctionNameConstants.CONDCHILD -> {
                    condChildFunctionEvaluator.evaluateCondChildFunction(
                        top,
                        evalStack,
                        programStack
                    )
                }
                top.functionName == FunctionNameConstants.QUOTE -> {
                    quoteFunctionEvaluator.evaluateQuoteFunction(
                        top,
                        evalStack,
                        programStack
                    )
                }
                top.currentParameterIndex < top.functionExpressionNode.children.size - 1 -> {
                    unfinishedProgramStackItemEvaluator.evaluateUnfinishedProgramStackItem(
                        top,
                        evalStack,
                        programStack
                    )
                }
                else -> {
                    finishedProgramStackItemEvaluator.evaluateFinishedProgramStackItem(
                        top,
                        userDefinedFunctions,
                        evalStack,
                        programStack
                    )
                }
            }
        }

        return evalStack[0]
    }
}