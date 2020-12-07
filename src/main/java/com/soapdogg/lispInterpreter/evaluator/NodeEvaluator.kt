package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.asserter.CondFunctionParameterAsserter
import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.comparator.NodeValueComparator
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.FunctionsConstants.functionLengthMap
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.exceptions.NotAListException
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever

class NodeEvaluator(
    private val atomNodeEvaluator: AtomNodeEvaluator,
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val numericStringDeterminer: NumericStringDeterminer,
    private val listValueRetriever: ListValueRetriever,
    private val listNotationPrinter: ListNotationPrinter,
    private val nodeValueComparator: NodeValueComparator,
    private val numericValueRetriever: NumericValueRetriever,
    private val nodeGenerator: NodeGenerator,
    private val condFunctionParameterAsserter: CondFunctionParameterAsserter
) {

    fun evaluateV2(
        nodes: List<NodeV2>,
        userDefinedFunctions: Map<String, UserDefinedFunction>,
        variableNameToValueMap: Map<String, NodeV2>
    ): List<NodeV2> {
        return nodes.map { node ->
            if (node is AtomNode) {
                atomNodeEvaluator.evaluate(
                    node,
                    variableNameToValueMap
                )
            } else {
                val expressionNode = node as ExpressionListNode
                val address = expressionNode.children[0] as AtomNode
                val addressValue = address.value
                if (addressValue == ReservedValuesConstants.NIL) {
                    address
                } else {

                    userDefinedFunctions[addressValue]?.let { it ->
                        val params = ExpressionListNode(expressionNode.children.subList(1, expressionNode.children.size))
                        functionLengthAsserter.assertLengthIsAsExpected(
                            addressValue,
                            it.formalParameters.size,
                            params
                        )

                        val newVariables: MutableMap<String, NodeV2> = HashMap(variableNameToValueMap)
                        for ((index, formal) in it.formalParameters.withIndex()) {
                            val a = params.children[index]
                            val evaluatedAddress = evaluateV2(
                                listOf(a),
                                userDefinedFunctions,
                                newVariables
                            )
                            newVariables[formal] = evaluatedAddress[0]
                        }

                        return@map evaluateV2(
                            listOf(it.body),
                            userDefinedFunctions,
                            newVariables
                        )[0]
                    }

                    if (addressValue == FunctionNameConstants.COND) {
                        val condParams = expressionNode.children.subList(1, expressionNode.children.size)
                        val condExpressionParams = condFunctionParameterAsserter.assertCondFunctionParameters(
                            condParams
                        )
                        condExpressionParams.forEach {
                            val evaluatedNode = evaluateV2(
                                it.children.subList(0, 1),
                                userDefinedFunctions,
                                variableNameToValueMap
                            )
                            if (evaluatedNode[0] is AtomNode && !nodeValueComparator.equalsNil((evaluatedNode[0] as AtomNode).value)) {
                                return@map evaluateV2(
                                    it.children.subList(1, 2),
                                    userDefinedFunctions,
                                    variableNameToValueMap
                                )[0]
                            }
                        }
                        throw NotAListException("Error! None of the conditions in the COND function evaluated to true.\n")
                    }

                    functionLengthMap[addressValue]?.let {
                        functionLengthAsserter.assertLengthIsAsExpected(
                            addressValue,
                            it,
                            expressionNode
                        )
                    }

                    when (addressValue) {
                        FunctionNameConstants.ATOM -> {
                            val evaluatedResult = evaluateV2(
                                listOf(expressionNode.children[1]),
                                userDefinedFunctions,
                                variableNameToValueMap
                            )
                            val result = evaluatedResult[0] !is ExpressionListNode
                            nodeGenerator.generateAtomNode(result)
                        }
                        FunctionNameConstants.INT -> {
                            val evaluatedResult = evaluateV2(
                                listOf(expressionNode.children[1]),
                                userDefinedFunctions,
                                variableNameToValueMap
                            )
                            if (evaluatedResult[0] is ExpressionListNode) {
                                nodeGenerator.generateAtomNode(false)
                            } else {
                                val value = (evaluatedResult[0] as AtomNode).value
                                val result = numericStringDeterminer.isStringNumeric(value)
                                nodeGenerator.generateAtomNode(result)
                            }
                        }
                        FunctionNameConstants.NULL -> {
                            val evaluatedResult = evaluateV2(
                                listOf(expressionNode.children[1]),
                                userDefinedFunctions,
                                variableNameToValueMap
                            )
                            if (evaluatedResult[0] is ExpressionListNode) {
                                nodeGenerator.generateAtomNode(false)
                            } else {
                                val value = (evaluatedResult[0] as AtomNode).value
                                val result = nodeValueComparator.equalsNil(value)
                                nodeGenerator.generateAtomNode(result)
                            }
                        }
                        FunctionNameConstants.QUOTE -> {
                            expressionNode.children[1]
                        }
                        FunctionNameConstants.CAR -> {
                            val evaluatedChild = evaluateV2(
                                listOf(expressionNode.children[1]),
                                userDefinedFunctions,
                                variableNameToValueMap
                            )
                            val evaluatedChildExpressionListNode = listValueRetriever.retrieveListValue(
                                evaluatedChild[0],
                                FunctionNameConstants.CAR,
                                variableNameToValueMap
                            )
                            evaluatedChildExpressionListNode.children[0]
                        }
                        FunctionNameConstants.CDR -> {
                            val evaluatedChild = evaluateV2(
                                listOf(expressionNode.children[1]),
                                userDefinedFunctions,
                                variableNameToValueMap
                            )
                            val evaluatedChildExpressionList = listValueRetriever.retrieveListValue(
                                evaluatedChild[0],
                                FunctionNameConstants.CDR,
                                variableNameToValueMap
                            )
                            if (evaluatedChildExpressionList.children.size == 1) {
                                evaluatedChildExpressionList.children[0]
                            } else if (evaluatedChildExpressionList.children.size == 2) {
                                evaluatedChildExpressionList.children[1]
                            } else {
                                nodeGenerator.generateExpressionListNode(
                                    evaluatedChildExpressionList.children.subList(1, evaluatedChildExpressionList.children.size)
                                )
                            }
                        }
                        FunctionNameConstants.CONS -> {
                            val evaluatedChildren = evaluateV2(
                                expressionNode.children.subList(1, 3),
                                userDefinedFunctions,
                                variableNameToValueMap
                            )
                            val evaluatedAddress = evaluatedChildren[0]
                            val evaluatedData = evaluatedChildren[1]
                            if (evaluatedData is ExpressionListNode) {
                                nodeGenerator.generateExpressionListNode(
                                    listOf(evaluatedAddress) + evaluatedData.children
                                )
                            } else {
                                nodeGenerator.generateExpressionListNode(
                                    listOf(evaluatedAddress, evaluatedData)
                                )
                            }
                        }
                        FunctionNameConstants.EQ -> {
                            val evaluatedChildren = evaluateV2(
                                expressionNode.children.subList(1, 3),
                                userDefinedFunctions,
                                variableNameToValueMap
                            )
                            val leftValue = listNotationPrinter.printInListNotation(evaluatedChildren[0])
                            val rightValue = listNotationPrinter.printInListNotation(evaluatedChildren[1])

                            val result = leftValue == rightValue
                            nodeGenerator.generateAtomNode(result)
                        }
                        FunctionNameConstants.GREATER -> {
                            val evaluatedChildren = evaluateV2(
                                expressionNode.children.subList(1, 3),
                                userDefinedFunctions,
                                variableNameToValueMap
                            )
                            val numericChildren = numericValueRetriever.retrieveNumericValue(
                                evaluatedChildren,
                                FunctionNameConstants.GREATER
                            )

                            val result = numericChildren[0] > numericChildren[1]
                            nodeGenerator.generateAtomNode(result)
                        }
                        FunctionNameConstants.LESS -> {
                            val evaluatedChildren = evaluateV2(
                                expressionNode.children.subList(1, 3),
                                userDefinedFunctions,
                                variableNameToValueMap
                            )
                            val numericChildren = numericValueRetriever.retrieveNumericValue(
                                evaluatedChildren,
                                FunctionNameConstants.LESS
                            )
                            val result = numericChildren[0] < numericChildren[1]
                            nodeGenerator.generateAtomNode(result)
                        }
                        FunctionNameConstants.MINUS -> {
                            val evaluatedChildren = evaluateV2(
                                expressionNode.children.subList(1, 3),
                                userDefinedFunctions,
                                variableNameToValueMap
                            )
                            val numericChildren = numericValueRetriever.retrieveNumericValue(
                                evaluatedChildren,
                                FunctionNameConstants.MINUS
                            )
                            val result = numericChildren.reduce { acc, i -> acc - i }
                            nodeGenerator.generateAtomNode(result)
                        }
                        FunctionNameConstants.PLUS -> {
                            val evaluatedChildren = evaluateV2(
                                expressionNode.children.subList(1, 3),
                                userDefinedFunctions,
                                variableNameToValueMap
                            )
                            val numericChildren = numericValueRetriever.retrieveNumericValue(
                                evaluatedChildren,
                                FunctionNameConstants.PLUS
                            )
                            val result = numericChildren.reduce { acc, i -> acc + i }
                            nodeGenerator.generateAtomNode(result)
                        }
                        FunctionNameConstants.TIMES -> {
                            val evaluatedChildren = evaluateV2(
                                expressionNode.children.subList(1, 3),
                                userDefinedFunctions,
                                variableNameToValueMap
                            )
                            val numericChildren = numericValueRetriever.retrieveNumericValue(
                                evaluatedChildren,
                                FunctionNameConstants.TIMES
                            )
                            val result = numericChildren.reduce { acc, i -> acc * i }
                            nodeGenerator.generateAtomNode(result)
                        }

                        else -> throw Exception("Error! Invalid CAR value: $addressValue\n")
                    }
                }
            }
        }
    }
}