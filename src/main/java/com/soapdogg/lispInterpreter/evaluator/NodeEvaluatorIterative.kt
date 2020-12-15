package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
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

            if (top.expressionListNode.children[0] is AtomNode) {
                val firstChild = top.expressionListNode.children[0] as AtomNode
                if (firstChild.value == FunctionNameConstants.COND) {
                    if (top.currentChildIndex == 0) {
                        programStack.push(StackItem(top.expressionListNode, top.currentChildIndex + 1))
                        for (i in top.expressionListNode.children.size - 2 downTo 1) {
                            programStack.push(StackItem(ExpressionListNode(listOf(AtomNode(FunctionNameConstants.CONDCHILD), top.expressionListNode.children[i])), 0))
                        }
                        continue
                    } else if (top.currentChildIndex == 2) {
                        if (programStack.isNotEmpty()) {
                            val head = programStack.pop()
                            programStack.push(StackItem(head.expressionListNode, head.currentChildIndex + 1))
                        }
                        continue
                    }
                }

                if (firstChild.value == FunctionNameConstants.CONDCHILD) {
                    val condChildExprNode = top.expressionListNode
                    val secondChild = condChildExprNode.children[1] as ExpressionListNode

                    if (top.currentChildIndex == 0) {
                        programStack.push(StackItem(top.expressionListNode, 1))
                        val secondChildsFirstChild = secondChild.children[0]
                        if (secondChildsFirstChild is ExpressionListNode) {
                            programStack.push(StackItem(secondChildsFirstChild, 0))
                        } else {
                            evalStack.push(secondChild.children[0])
                        }
                    } else {
                        val evaluatedCondChild = evalStack.pop() as AtomNode
                        if (evaluatedCondChild.value != ReservedValuesConstants.NIL) {
                            evalStack.push(secondChild.children[1])
                            while (
                                (programStack.peek().expressionListNode.children[0] as AtomNode).value == FunctionNameConstants.CONDCHILD
                            ) {
                                programStack.pop()
                            }
                            val cond = programStack.pop()
                            programStack.push(StackItem(cond.expressionListNode, 2))
                        }
                    }
                    continue
                }
            }

            val nthChild = top.expressionListNode.children[top.currentChildIndex]
            if (nthChild is ExpressionListNode) {
                programStack.push(top)
                programStack.push(StackItem(nthChild, 0))
                continue
            }

            val nthChildAtomNode = nthChild as AtomNode
            if (nthChildAtomNode.value == FunctionNameConstants.QUOTE) {
                val quoteExprNode = top.expressionListNode
                val secondChild = quoteExprNode.children[1]
                evalStack.push(secondChild)
                if (programStack.isNotEmpty()) {
                    val head = programStack.pop()
                    programStack.push(StackItem(head.expressionListNode, head.currentChildIndex + 1))
                }
                continue
            }

            val expectedFunctionLength = functionLengthDeterminer.determineFunctionLength(top.expressionListNode)
            if (top.currentChildIndex < expectedFunctionLength) {
                evalStack.push(nthChildAtomNode)
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