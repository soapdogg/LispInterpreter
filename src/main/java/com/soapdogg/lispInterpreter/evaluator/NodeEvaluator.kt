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
        node: List<NodeV2>,
        userDefinedFunctions: Map<String, UserDefinedFunction>,
        variableNameToValueMap: Map<String, NodeV2>
    ): List<NodeV2> {
        return node.map {
            if (it is AtomNode) {
                atomNodeEvaluator.evaluate(
                    it,
                    variableNameToValueMap
                )
            } else {
                val expressionNode = it as ExpressionListNode
                val address = expressionNode.children[0] as AtomNode
                val addressValue = address.value
                if (addressValue == ReservedValuesConstants.NIL) {
                    return listOf(address)
                }

                userDefinedFunctions[addressValue]?.let {
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

                    return evaluateV2(
                        listOf(it.body),
                        userDefinedFunctions,
                        newVariables
                    )
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
                    return listOf(nodeGenerator.generateAtomNode(result))
                }
                FunctionNameConstants.INT -> {
                    val evaluatedResult = evaluateV2(
                        listOf(expressionNode.children[1]),
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    if (evaluatedResult[0] is ExpressionListNode) {
                        return listOf(nodeGenerator.generateAtomNode(false))
                    }
                    val value = (evaluatedResult[0] as AtomNode).value
                    val result = numericStringDeterminer.isStringNumeric(value)
                    return listOf(nodeGenerator.generateAtomNode(result))
                }
                FunctionNameConstants.NULL -> {
                    val evaluatedResult = evaluateV2(
                        listOf(expressionNode.children[1]),
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    if (evaluatedResult[0] is ExpressionListNode) {
                        return listOf(nodeGenerator.generateAtomNode(false))
                    }
                    val value = (evaluatedResult[0] as AtomNode).value
                    val result = nodeValueComparator.equalsNil(value)
                    return listOf(nodeGenerator.generateAtomNode(result))
                }
                FunctionNameConstants.QUOTE -> {
                    return listOf(expressionNode.children[1])
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
                    return listOf(evaluatedChildExpressionListNode.children[0])
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
                        return listOf(evaluatedChildExpressionList.children[0])
                    }
                    if (evaluatedChildExpressionList.children.size == 2) {
                        return listOf(evaluatedChildExpressionList.children[1])
                    }
                    return listOf(
                        nodeGenerator.generateExpressionListNode(
                            evaluatedChildExpressionList.children.subList(1, evaluatedChildExpressionList.children.size)
                        )
                    )
                }
                FunctionNameConstants.CONS -> {
                    val evaluatedAddress = evaluateV2(
                        listOf(expressionNode.children[1]),
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val evaluatedData = evaluateV2(
                        listOf(expressionNode.children[2]),
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    if (evaluatedData[0] is ExpressionListNode) {
                        return listOf(
                            nodeGenerator.generateExpressionListNode(
                            listOf(evaluatedAddress[0]) + (evaluatedData[0] as ExpressionListNode).children
                            )
                        )
                    }
                    return listOf(
                        nodeGenerator.generateExpressionListNode(
                            listOf(evaluatedAddress[0], evaluatedData[0])
                        )
                    )
                }
                FunctionNameConstants.EQ -> {
                    val evaluatedAddress = evaluateV2(
                        listOf(expressionNode.children[1]),
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val evaluatedData = evaluateV2(
                        listOf(expressionNode.children[2]),
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val leftValue = listNotationPrinter.printInListNotation(evaluatedAddress)
                    val rightValue = listNotationPrinter.printInListNotation(evaluatedData)

                    val result = leftValue == rightValue
                    return listOf(nodeGenerator.generateAtomNode(result))
                }
                FunctionNameConstants.GREATER -> {
                    val evaluatedAddress = evaluateV2(
                        listOf(expressionNode.children[1]),
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val evaluatedData = evaluateV2(
                        listOf(expressionNode.children[2]),
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val leftValue = numericValueRetriever.retrieveNumericValue(
                        evaluatedAddress[0],
                        1,
                        FunctionNameConstants.GREATER
                    )
                    val rightValue = numericValueRetriever.retrieveNumericValue(
                        evaluatedData[0],
                        2,
                        FunctionNameConstants.GREATER
                    )
                    val result = leftValue > rightValue
                    return listOf(nodeGenerator.generateAtomNode(result))
                }
                FunctionNameConstants.LESS -> {
                    val evaluatedAddress = evaluateV2(
                        listOf(expressionNode.children[1]),
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val evaluatedData = evaluateV2(
                        listOf(expressionNode.children[2]),
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val leftValue = numericValueRetriever.retrieveNumericValue(
                        evaluatedAddress[0],
                        1,
                        FunctionNameConstants.LESS
                    )
                    val rightValue = numericValueRetriever.retrieveNumericValue(
                        evaluatedData[0],
                        2,
                        FunctionNameConstants.LESS
                    )
                    val result = leftValue < rightValue
                    return listOf(nodeGenerator.generateAtomNode(result))
                }
                FunctionNameConstants.MINUS -> {
                    val evaluatedAddress = evaluateV2(
                        listOf(expressionNode.children[1]),
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val evaluatedData = evaluateV2(
                        listOf(expressionNode.children[2]),
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val leftValue = numericValueRetriever.retrieveNumericValue(
                        evaluatedAddress[0],
                        1,
                        FunctionNameConstants.MINUS
                    )
                    val rightValue = numericValueRetriever.retrieveNumericValue(
                        evaluatedData[0],
                        2,
                        FunctionNameConstants.MINUS
                    )
                    val result = leftValue - rightValue
                    return listOf(nodeGenerator.generateAtomNode(result))
                }
                FunctionNameConstants.PLUS -> {
                    val evaluatedAddress = evaluateV2(
                        listOf(expressionNode.children[1]),
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val evaluatedData = evaluateV2(
                        listOf(expressionNode.children[2]),
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val leftValue = numericValueRetriever.retrieveNumericValue(
                        evaluatedAddress[0],
                        1,
                        FunctionNameConstants.PLUS
                    )
                    val rightValue = numericValueRetriever.retrieveNumericValue(
                        evaluatedData[0],
                        2,
                        FunctionNameConstants.PLUS
                    )
                    val result = leftValue + rightValue
                    return listOf(nodeGenerator.generateAtomNode(result))
                }
                FunctionNameConstants.TIMES -> {
                    val evaluatedAddress = evaluateV2(
                        listOf(expressionNode.children[1]),
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val evaluatedData = evaluateV2(
                        listOf(expressionNode.children[2]),
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val leftValue = numericValueRetriever.retrieveNumericValue(
                        evaluatedAddress[0],
                        1,
                        FunctionNameConstants.TIMES
                    )
                    val rightValue = numericValueRetriever.retrieveNumericValue(
                        evaluatedData[0],
                        2,
                        FunctionNameConstants.TIMES
                    )
                    val result = leftValue * rightValue
                    return listOf(nodeGenerator.generateAtomNode(result))
                }
                FunctionNameConstants.COND -> {
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
                            return evaluateV2(
                                it.children.subList(1, 2),
                                userDefinedFunctions,
                                variableNameToValueMap
                            )
                        }
                    }
                    throw NotAListException("Error! None of the conditions in the COND function evaluated to true.\n")
                }
                else -> throw Exception("Error! Invalid CAR value: $addressValue\n")
            }
        }
        }
    }
}