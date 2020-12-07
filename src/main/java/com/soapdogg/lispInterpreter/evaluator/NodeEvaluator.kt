package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.comparator.NodeValueComparator
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.FunctionsConstants
import com.soapdogg.lispInterpreter.constants.FunctionsConstants.functionLengthMap
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.determiner.UserDefinedFunctionNameDeterminer
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever

class NodeEvaluator(
    private val atomNodeEvaluator: AtomNodeEvaluator,
    private val userDefinedFunctionNameDeterminer: UserDefinedFunctionNameDeterminer,
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val numericStringDeterminer: NumericStringDeterminer,
    private val listNotationPrinter: ListNotationPrinter,
    private val nodeValueComparator: NodeValueComparator,
    private val numericValueRetriever: NumericValueRetriever,
    private val nodeGenerator: NodeGenerator
) {

    fun evaluateV2(
        node: NodeV2,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, NodeV2>
    ): NodeV2 {
        return if (node is AtomNode) {
            atomNodeEvaluator.evaluate(
                node,
                variableNameToValueMap
            )
        } else {
            val expressionNode = node as ExpressionListNode
            val address = expressionNode.children[0] as AtomNode
            val addressValue = address.value
            if (addressValue == ReservedValuesConstants.NIL) {
                return address
            }
            val isFunctionName = userDefinedFunctionNameDeterminer.determineIfUserDefinedFunctionName(
                userDefinedFunctions,
                addressValue
            )
            if (isFunctionName) {
                val userDefinedFunction = userDefinedFunctions.stream().filter { (_, _, functionName) -> functionName == addressValue }.findFirst().get()
                val params = ExpressionListNode(expressionNode.children.subList(1, expressionNode.children.size))
                functionLengthAsserter.assertLengthIsAsExpected(
                    userDefinedFunction.functionName,
                    userDefinedFunction.formalParameters.size,
                    params
                )
                val newVariables: MutableMap<String, NodeV2> = HashMap(variableNameToValueMap)
                for ((index, formal) in userDefinedFunction.formalParameters.withIndex()) {
                    val a = params.children[index]
                    val evaluatedAddress = evaluateV2(
                        a,
                        userDefinedFunctions,
                        newVariables
                    )
                    newVariables[formal] = evaluatedAddress
                }

                return evaluateV2(
                    userDefinedFunction.body,
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

            when {
                addressValue == FunctionNameConstants.ATOM -> {
                    val evaluatedResult = evaluateV2(
                        expressionNode.children[1],
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val result = evaluatedResult !is ExpressionListNode
                    return nodeGenerator.generateAtomNode(result)
                }
                addressValue == FunctionNameConstants.INT -> {
                    val evaluatedResult = evaluateV2(
                        expressionNode.children[1],
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    if (evaluatedResult is ExpressionListNode) {
                        return nodeGenerator.generateAtomNode(false)
                    }
                    val value = (evaluatedResult as AtomNode).value
                    val result = numericStringDeterminer.isStringNumeric(value)
                    return nodeGenerator.generateAtomNode(result)
                }
                addressValue == FunctionNameConstants.NULL -> {
                    val evaluatedResult = evaluateV2(
                        expressionNode.children[1],
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    if (evaluatedResult is ExpressionListNode) {
                        return nodeGenerator.generateAtomNode(false)
                    }
                    val value = (evaluatedResult as AtomNode).value
                    val result = nodeValueComparator.equalsNil(value)
                    return nodeGenerator.generateAtomNode(result)
                }
                addressValue == FunctionNameConstants.QUOTE -> {
                    return expressionNode.children[1]
                }
                addressValue == FunctionNameConstants.EQ -> {
                    val evaluatedAddress = evaluateV2(
                        expressionNode.children[1],
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val evaluatedData = evaluateV2(
                        expressionNode.children[2],
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val leftValue = listNotationPrinter.printInListNotation(evaluatedAddress)
                    val rightValue = listNotationPrinter.printInListNotation(evaluatedData)

                    val result = leftValue == rightValue
                    return nodeGenerator.generateAtomNode(result)
                }
                addressValue == FunctionNameConstants.GREATER -> {
                    val evaluatedAddress = evaluateV2(
                        expressionNode.children[1],
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val evaluatedData = evaluateV2(
                        expressionNode.children[2],
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val leftValue = numericValueRetriever.retrieveNumericValue(
                        evaluatedAddress,
                        1,
                        FunctionNameConstants.GREATER
                    )
                    val rightValue = numericValueRetriever.retrieveNumericValue(
                        evaluatedData,
                        2,
                        FunctionNameConstants.GREATER
                    )
                    val result = leftValue > rightValue
                    return nodeGenerator.generateAtomNode(result)
                }
                addressValue == FunctionNameConstants.LESS -> {
                    val evaluatedAddress = evaluateV2(
                        expressionNode.children[1],
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val evaluatedData = evaluateV2(
                        expressionNode.children[2],
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val leftValue = numericValueRetriever.retrieveNumericValue(
                        evaluatedAddress,
                        1,
                        FunctionNameConstants.LESS
                    )
                    val rightValue = numericValueRetriever.retrieveNumericValue(
                        evaluatedData,
                        2,
                        FunctionNameConstants.LESS
                    )
                    val result = leftValue < rightValue
                    return nodeGenerator.generateAtomNode(result)
                }
                addressValue == FunctionNameConstants.MINUS -> {
                    val evaluatedAddress = evaluateV2(
                        expressionNode.children[1],
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val evaluatedData = evaluateV2(
                        expressionNode.children[2],
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val leftValue = numericValueRetriever.retrieveNumericValue(
                        evaluatedAddress,
                        1,
                        FunctionNameConstants.MINUS
                    )
                    val rightValue = numericValueRetriever.retrieveNumericValue(
                        evaluatedData,
                        2,
                        FunctionNameConstants.MINUS
                    )
                    val result = leftValue - rightValue
                    return nodeGenerator.generateAtomNode(result)
                }
                addressValue == FunctionNameConstants.PLUS -> {
                    val evaluatedAddress = evaluateV2(
                        expressionNode.children[1],
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val evaluatedData = evaluateV2(
                        expressionNode.children[2],
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val leftValue = numericValueRetriever.retrieveNumericValue(
                        evaluatedAddress,
                        1,
                        FunctionNameConstants.PLUS
                    )
                    val rightValue = numericValueRetriever.retrieveNumericValue(
                        evaluatedData,
                        2,
                        FunctionNameConstants.PLUS
                    )
                    val result = leftValue + rightValue
                    return nodeGenerator.generateAtomNode(result)
                }
                addressValue == FunctionNameConstants.TIMES -> {
                    val evaluatedAddress = evaluateV2(
                        expressionNode.children[1],
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val evaluatedData = evaluateV2(
                        expressionNode.children[2],
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                    val leftValue = numericValueRetriever.retrieveNumericValue(
                        evaluatedAddress,
                        1,
                        FunctionNameConstants.TIMES
                    )
                    val rightValue = numericValueRetriever.retrieveNumericValue(
                        evaluatedData,
                        2,
                        FunctionNameConstants.TIMES
                    )
                    val result = leftValue * rightValue
                    return nodeGenerator.generateAtomNode(result)
                }
                FunctionsConstants.functionV2Map!!.containsKey(addressValue) -> {

                    val function = FunctionsConstants.functionV2Map[addressValue]
                    return function!!.evaluateLispFunction(
                        expressionNode,
                        userDefinedFunctions,
                        variableNameToValueMap
                    )
                }
                else -> throw Exception("Error! Invalid CAR value: $addressValue\n")
            }

        }
    }
}