package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.StackItem
import com.soapdogg.lispInterpreter.determiner.FunctionLengthDeterminer
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever
import java.util.*

class NodeEvaluatorIterative (
    private val functionLengthDeterminer: FunctionLengthDeterminer,
    private val nodeGenerator: NodeGenerator,
    private val numericStringDeterminer: NumericStringDeterminer,
    private val numericValueRetriever: NumericValueRetriever
){

    fun evaluate(
        expressionListNode: ExpressionListNode
    ) {

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


            evalStack.push(temp.node)

            while (
                !stack.isEmpty()
                && stack.peek().node is ExpressionListNode
                && temp.childrenIndex == functionLengthDeterminer.determineFunctionLength(stack.peek().node) - 1) {
                temp = stack.pop()

                val functionLength = functionLengthDeterminer.determineFunctionLength(temp.node)
                val functionStack = Stack<NodeV2>()
                for (i in 0 until functionLength) {
                    functionStack.push(evalStack.pop())
                }

                val function = functionStack.pop() as AtomNode
                if (function.value == FunctionNameConstants.INT) {
                    val first = functionStack.pop()
                    val resultingNode = if (first is AtomNode) {
                        val value = first.value
                        val isNumeric = numericStringDeterminer.isStringNumeric(value)
                        nodeGenerator.generateAtomNode(isNumeric)
                    } else {
                        nodeGenerator.generateAtomNode(false)
                    }
                    evalStack.push(resultingNode)
                } else if (function.value == FunctionNameConstants.NULL) {
                    val first = functionStack.pop()
                    val resultingNode = if (first is AtomNode) {
                        val value = first.value
                        val isNil = value == ReservedValuesConstants.NIL
                        nodeGenerator.generateAtomNode(isNil)
                    } else {
                        nodeGenerator.generateAtomNode(false)
                    }
                    evalStack.push(resultingNode)
                } else if (function.value == FunctionNameConstants.ATOM) {
                    val first = functionStack.pop()
                    val isAtom = first !is ExpressionListNode
                    val resultingNode = nodeGenerator.generateAtomNode(isAtom)
                    evalStack.push(resultingNode)
                } else if (function.value == FunctionNameConstants.CONS) {
                    val evaluatedAddress = functionStack.pop()
                    val evaluatedData = functionStack.pop()

                    val resultingNode = if (evaluatedData is ExpressionListNode) {
                        nodeGenerator.generateExpressionListNode(
                            listOf(evaluatedAddress) + evaluatedData.children
                        )
                    } else {
                        nodeGenerator.generateExpressionListNode(
                            listOf(evaluatedAddress, evaluatedData)
                        )
                    }

                    evalStack.push(resultingNode)
                } else if (function.value == FunctionNameConstants.EQ) {
                    val first = functionStack.pop()
                    val second = functionStack.pop()
                    val isEqual = first == second
                    val resultingNode = nodeGenerator.generateAtomNode(isEqual)
                    evalStack.push(resultingNode)
                } else if (function.value == FunctionNameConstants.GREATER) {
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

                    val result = firstNumeric > secondNumeric

                    val resultingNode = nodeGenerator.generateAtomNode(result)
                    evalStack.push(resultingNode)
                } else if (function.value == FunctionNameConstants.LESS) {
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

                    val result = firstNumeric < secondNumeric

                    val resultingNode = nodeGenerator.generateAtomNode(result)
                    evalStack.push(resultingNode)
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

                    val resultingNode = nodeGenerator.generateAtomNode(result)
                    evalStack.push(resultingNode)
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
                    val resultingNode = nodeGenerator.generateAtomNode(result)
                    evalStack.push(resultingNode)
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

                    val resultingNode = nodeGenerator.generateAtomNode(result)
                    evalStack.push(resultingNode)
                }
            }
            if (stack.isNotEmpty()) {
                root = (stack.peek().node as ExpressionListNode).children[
                    temp.childrenIndex + 1
                ]
                currentRootIndex = temp.childrenIndex + 1
            }
        }

        println(evalStack[0])
    }
}