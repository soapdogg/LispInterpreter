package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.StackItem
import com.soapdogg.lispInterpreter.determiner.FunctionLengthDeterminer
import com.soapdogg.lispInterpreter.function.Function
import java.util.*

class NodeEvaluatorIterative(
    private val functionLengthDeterminer: FunctionLengthDeterminer,
    private val functionMap: Map<String, Function>
){

    fun evaluate(
        expressionListNode: ExpressionListNode
    ): NodeV2 {

        val programStack = Stack<StackItem>()
        val evalStack = Stack<NodeV2>()

        programStack.push(StackItem(expressionListNode, 0))

        while(
            programStack.isNotEmpty()
        ) {
            val top = programStack.pop()
            val nthChild = top.expressionListNode.children[top.currentChildIndex]
            if (nthChild is ExpressionListNode) {
                programStack.push(top)
                programStack.push(StackItem(nthChild, 0))
                continue
            }

            val expectedFunctionLength = functionLengthDeterminer.determineFunctionLength(top.expressionListNode)
            if (top.currentChildIndex < expectedFunctionLength) {
                evalStack.push(nthChild)
                programStack.push(StackItem(top.expressionListNode, top.currentChildIndex + 1))
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
                if (functionMap.containsKey(functionName)) {
                    val function = functionMap.getValue(functionName)
                    val evaluatedFunctionResult = function.evaluate(functionStack)
                    evalStack.push(evaluatedFunctionResult)
                }

                programStack.pop() // remove ExpressionNode from program stack since we have evaluated it

                if (programStack.isNotEmpty()) {
                    val head = programStack.pop()
                    programStack.push(StackItem(head.expressionListNode, head.currentChildIndex + 1))
                }
            }
        }

        return evalStack[0]
    }
}