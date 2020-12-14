package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.determiner.FunctionLengthDeterminer
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever
import java.util.*

class NodeEvaluatorIterative (
    private val functionLengthDeterminer: FunctionLengthDeterminer,
    private val numericStringDeterminer: NumericStringDeterminer,
    private val numericValueRetriever: NumericValueRetriever
){

    fun evaluate(
        expressionListNode: ExpressionListNode
    ) {

        val stack = Stack<Pair<NodeV2, Int>>()
        var currentRootIndex = 0
        var root: NodeV2? = expressionListNode
        val evalStack = Stack<NodeV2>()

        while(
            root != null || stack.isNotEmpty()
        ) {
            if (root != null) {
                stack.push(Pair(root, currentRootIndex))
                currentRootIndex = 0

                if (root is ExpressionListNode) {
                    root = root.children[0]
                } else {
                    root = null
                }
                continue
            }

            var temp = stack.pop()



            evalStack.push(temp.first)

            while (
                !stack.isEmpty()
                && stack.peek().first is ExpressionListNode
                && temp.second == functionLengthDeterminer.determineFunctionLength(stack.peek().first) - 1) {
                temp = stack.pop()

                val functionLength = functionLengthDeterminer.determineFunctionLength(temp.first)
                val functionStack = Stack<NodeV2>()
                for (i in 0 until functionLength) {
                    functionStack.push(evalStack.pop())
                }

                val function = functionStack.pop() as AtomNode
                if (function.value == FunctionNameConstants.INT) {
                    val first = functionStack.pop()
                    if (first is AtomNode) {
                        val value = first.value
                        val isNumeric = numericStringDeterminer.isStringNumeric(value)
                        if (isNumeric) {
                            evalStack.push(AtomNode(ReservedValuesConstants.T))
                        } else {
                            evalStack.push(AtomNode(ReservedValuesConstants.NIL))
                        }
                    } else {
                        evalStack.push(AtomNode(ReservedValuesConstants.NIL))
                    }
                } else if (function.value == FunctionNameConstants.NULL) {
                    val first = functionStack.pop()
                    if (first is AtomNode) {
                        val value = first.value
                        val isNil = value == ReservedValuesConstants.NIL
                        if (isNil) {
                            evalStack.push(AtomNode(ReservedValuesConstants.T))
                        } else {
                            evalStack.push(AtomNode(ReservedValuesConstants.NIL))
                        }
                    } else {
                        evalStack.push(AtomNode(ReservedValuesConstants.NIL))
                    }
                } else if (function.value == FunctionNameConstants.EQ) {
                    val first = functionStack.pop()
                    val second = functionStack.pop()
                    if (first == second) {
                        evalStack.push(AtomNode(ReservedValuesConstants.T))
                    } else {
                        evalStack.push(AtomNode(ReservedValuesConstants.NIL))
                    }
                } else if (function.value == FunctionNameConstants.PLUS) {
                    val first = functionStack.pop()
                    val second = functionStack.pop()
                    val firstNumeric = numericValueRetriever.retrieveNumericValue(
                        first,
                        function.value,
                        1
                    )
                    val secondNumeric = numericValueRetriever.retrieveNumericValue(
                        second,
                        function.value,
                        2
                    )

                    val result = firstNumeric + secondNumeric

                    evalStack.push(AtomNode(result.toString()))
                } else if (function.value == FunctionNameConstants.MINUS) {
                    val first = functionStack.pop()
                    val second = functionStack.pop()
                    val firstNumeric = numericValueRetriever.retrieveNumericValue(
                        first,
                        function.value,
                        1
                    )
                    val secondNumeric = numericValueRetriever.retrieveNumericValue(
                        second,
                        function.value,
                        2
                    )

                    val result = firstNumeric - secondNumeric

                    evalStack.push(AtomNode(result.toString()))
                } else if (function.value == FunctionNameConstants.TIMES) {
                    val first = functionStack.pop()
                    val second = functionStack.pop()
                    val firstNumeric = numericValueRetriever.retrieveNumericValue(
                        first,
                        function.value,
                        1
                    )
                    val secondNumeric = numericValueRetriever.retrieveNumericValue(
                        second,
                        function.value,
                        2
                    )

                    val result = firstNumeric * secondNumeric

                    evalStack.push(AtomNode(result.toString()))
                }
            }
            if (stack.isNotEmpty()) {
                root = (stack.peek().first as ExpressionListNode).children[
                    temp.second + 1
                ]
                currentRootIndex = temp.second + 1
            }
        }

        println(evalStack[0])
    }
}