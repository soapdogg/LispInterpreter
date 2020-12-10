package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import java.util.*

class StackEvaluator {

    fun evaluate(
        stack: Stack<AtomNode>
    ): Stack<AtomNode> {
        val programStack = Stack<AtomNode>()
        while(stack.isNotEmpty()) {
            val head = stack.pop()

            val value = head.value
            if (value == FunctionNameConstants.MINUS) {
                val first = programStack.last()
                programStack.remove(first)
                val second = programStack.last()
                programStack.remove(second)
                val result = first.value.toInt() - second.value.toInt()
                programStack.push(AtomNode(result.toString()))
            } else if (value == FunctionNameConstants.TIMES) {
                val first = programStack.last()
                programStack.remove(first)
                val second = programStack.last()
                programStack.remove(second)
                val result = first.value.toInt() * second.value.toInt()
                programStack.push(AtomNode(result.toString()))
            } else {
                programStack.push(head)
            }
        }
        return programStack
    }
}