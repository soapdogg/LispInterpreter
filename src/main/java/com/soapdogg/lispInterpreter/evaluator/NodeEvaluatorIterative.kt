package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
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

        val stack = Stack<StackItem>()
        var currentRootIndex = 0
        var root: NodeV2? = expressionListNode
        val evalStack = Stack<NodeV2>()

        while(
            root != null || stack.isNotEmpty()
        ) {
            if (root != null) {
                stack.push(StackItem(root, currentRootIndex))
                currentRootIndex = 0

                if (root is ExpressionListNode) {
                    root = root.children[0]
                } else {
                    root = null
                }
                continue
            }

            var temp = stack.pop()
            if (temp.node is AtomNode && (temp.node as AtomNode).value == FunctionNameConstants.QUOTE) {
                val quoteExpr = stack.pop()
                val quoteExprNode = quoteExpr.node as ExpressionListNode
                if (stack.isNotEmpty()) {
                    val top = stack.pop()
                    stack.push(StackItem(top.node, top.childrenIndex + 1))
                    temp = stack.peek()
                }
                root = null
                evalStack.push(quoteExprNode.children[1])
            } else {
                evalStack.push(temp.node)
            }

            while (
                stack.isNotEmpty()
                &&
                temp.childrenIndex == functionLengthDeterminer.determineFunctionLength(stack.peek().node) - 1
            ) {
                temp = stack.pop()

                val functionLength = functionLengthDeterminer.determineFunctionLength(temp.node)
                val functionStack = Stack<NodeV2>()
                for (i in 0 until functionLength) {
                    functionStack.push(evalStack.pop())
                }

                val function = functionStack.pop() as AtomNode
                val functionValue = function.value
                if (functionMap.containsKey(functionValue)) {
                    val f = functionMap.getValue(functionValue)
                    val resultingNode = f.evaluate(functionStack)
                    evalStack.push(resultingNode)
                }
            }


            if (stack.isNotEmpty()) {
                currentRootIndex = temp.childrenIndex + 1
                root = (stack.peek().node as ExpressionListNode).children[
                    currentRootIndex
                ]
            }
        }

        return evalStack[0]
    }
}