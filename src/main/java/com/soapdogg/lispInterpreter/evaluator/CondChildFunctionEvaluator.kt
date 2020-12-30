package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.MyStack
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import java.util.*

class CondChildFunctionEvaluator(
    private val stackUpdateDeterminer: StackUpdateDeterminer
) {

    fun evaluateCondChildFunction(
        top: ProgramStackItem,
        evalStack: MyStack<NodeV2>,
        programStack: MyStack<ProgramStackItem>
    ) {
        programStack.push(top)
        val condChildCurrentParam = top.functionExpressionNode.children[top.currentParameterIndex +1]
        if (top.currentParameterIndex == 0) {
            stackUpdateDeterminer.determineHowToUpdateStacks(
                condChildCurrentParam,
                top.variableMap,
                evalStack,
                programStack
            )
        }
        else {
            programStack.pop()
            val evaluatedCondChild = evalStack.pop() as AtomNode
            if (evaluatedCondChild.value != ReservedValuesConstants.NIL) {
                while (
                    programStack.peek().functionName != FunctionNameConstants.COND
                ) {
                    programStack.pop()
                }
                programStack.pop() //CondProgramStackItem
                stackUpdateDeterminer.determineHowToUpdateStacks(
                    condChildCurrentParam,
                    top.variableMap,
                    evalStack,
                    programStack
                )
            }
        }
    }
}