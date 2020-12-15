package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.asserter.CondFunctionParameterAsserter
import com.soapdogg.lispInterpreter.comparator.NodeValueComparator
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.FunctionsConstants.functionLengthMap
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.exceptions.NotAListException
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever

class NodeEvaluator(
    private val atomNodeEvaluator: AtomNodeEvaluator,
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
                        val evaluatedAddress = evaluateV2(
                            expressionNode.children.subList(1, expressionNode.children.size),
                            userDefinedFunctions,
                            variableNameToValueMap
                        )

                        val newVariables = variableNameToValueMap + it.formalParameters.withIndex().map{
                            Pair(it.value, evaluatedAddress[it.index])
                        }.toMap()

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

                        if (addressValue == FunctionNameConstants.QUOTE) {
                            return@map expressionNode.children[1]
                        }

                        val evaluatedChildren = evaluateV2(
                            expressionNode.children.subList(1, it),
                            userDefinedFunctions,
                            variableNameToValueMap
                        )

                        when (addressValue) {
                            FunctionNameConstants.ATOM -> {
                                val result = evaluatedChildren[0] !is ExpressionListNode
                                return@map nodeGenerator.generateAtomNode(result)
                            }
                            FunctionNameConstants.NULL -> {
                                if (evaluatedChildren[0] is ExpressionListNode) {
                                    return@map nodeGenerator.generateAtomNode(false)
                                } else {
                                    val value = (evaluatedChildren[0] as AtomNode).value
                                    val result = nodeValueComparator.equalsNil(value)
                                    return@map nodeGenerator.generateAtomNode(result)
                                }
                            }
                            FunctionNameConstants.CAR -> {
                                val evaluatedChildExpressionListNode = listValueRetriever.retrieveListValue(
                                    evaluatedChildren[0],
                                    FunctionNameConstants.CAR,
                                    variableNameToValueMap
                                )
                                return@map evaluatedChildExpressionListNode.children[0]
                            }
                            FunctionNameConstants.CDR -> {
                                val evaluatedChildExpressionList = listValueRetriever.retrieveListValue(
                                    evaluatedChildren[0],
                                    FunctionNameConstants.CDR,
                                    variableNameToValueMap
                                )
                                when (evaluatedChildExpressionList.children.size) {
                                    1 -> {
                                        return@map evaluatedChildExpressionList.children[0]
                                    }
                                    2 -> {
                                        return@map evaluatedChildExpressionList.children[1]
                                    }
                                    else -> {
                                        return@map nodeGenerator.generateExpressionListNode(
                                            evaluatedChildExpressionList.children.subList(1, evaluatedChildExpressionList.children.size)
                                        )
                                    }
                                }
                            }
                            FunctionNameConstants.CONS -> {
                                val evaluatedAddress = evaluatedChildren[0]
                                val evaluatedData = evaluatedChildren[1]
                                if (evaluatedData is ExpressionListNode) {
                                    return@map nodeGenerator.generateExpressionListNode(
                                        listOf(evaluatedAddress) + evaluatedData.children
                                    )
                                } else {
                                    return@map nodeGenerator.generateExpressionListNode(
                                        listOf(evaluatedAddress, evaluatedData)
                                    )
                                }
                            }
                            FunctionNameConstants.EQ -> {
                                val leftValue = listNotationPrinter.printInListNotation(evaluatedChildren[0])
                                val rightValue = listNotationPrinter.printInListNotation(evaluatedChildren[1])

                                val result = leftValue == rightValue
                                return@map nodeGenerator.generateAtomNode(result)
                            }
                            FunctionNameConstants.GREATER -> {
                                val numericChildren = numericValueRetriever.retrieveNumericValue(
                                    evaluatedChildren,
                                    FunctionNameConstants.GREATER
                                )

                                val result = numericChildren[0] > numericChildren[1]
                                return@map nodeGenerator.generateAtomNode(result)
                            }
                            FunctionNameConstants.LESS -> {
                                val numericChildren = numericValueRetriever.retrieveNumericValue(
                                    evaluatedChildren,
                                    FunctionNameConstants.LESS
                                )
                                val result = numericChildren[0] < numericChildren[1]
                                return@map nodeGenerator.generateAtomNode(result)
                            }
                            FunctionNameConstants.MINUS -> {
                                val numericChildren = numericValueRetriever.retrieveNumericValue(
                                    evaluatedChildren,
                                    FunctionNameConstants.MINUS
                                )
                                val result = numericChildren.reduce { acc, i -> acc - i }
                                return@map nodeGenerator.generateAtomNode(result)
                            }
                            FunctionNameConstants.PLUS -> {
                                val numericChildren = numericValueRetriever.retrieveNumericValue(
                                    evaluatedChildren,
                                    FunctionNameConstants.PLUS
                                )
                                val result = numericChildren.reduce { acc, i -> acc + i }
                                return@map nodeGenerator.generateAtomNode(result)
                            }
                            FunctionNameConstants.TIMES -> {
                                val numericChildren = numericValueRetriever.retrieveNumericValue(
                                    evaluatedChildren,
                                    FunctionNameConstants.TIMES
                                )
                                val result = numericChildren.reduce { acc, i -> acc * i }
                                return@map nodeGenerator.generateAtomNode(result)
                            }
                            else -> return@let
                        }
                    }
                    throw Exception("Error! Invalid CAR value: $addressValue\n")
                }
            }
        }
    }
}