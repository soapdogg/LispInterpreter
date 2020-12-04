package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.Node


class GreaterFunction (
    private val nodeConverter: NodeConverter,
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val nodeEvaluator: NodeEvaluator,
    private val numericValueRetriever: NumericValueRetriever,
    private val listValueRetriever: ListValueRetriever,
    private val nodeGenerator: NodeGenerator
): LispFunction {

    override fun evaluateLispFunction(
        params: Node,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, Node>
    ): Node {
        val paramsV2 = nodeConverter.convertNodeToNodeV2(params)
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.GREATER,
            FunctionLengthConstants.TWO,
            paramsV2
        )
        val evaluatedAddress = nodeEvaluator.evaluate(
            paramsV2,
            userDefinedFunctions,
            variableNameToValueMap,
            true
        )
        val leftValue = numericValueRetriever.retrieveNumericValue(
            evaluatedAddress,
            1,
            FunctionNameConstants.GREATER
        )
        val expressionNodeParams = listValueRetriever.retrieveListValue(
            params,
            FunctionNameConstants.GREATER,
            variableNameToValueMap
        )
        val data = expressionNodeParams.data
        val evaluatedData = nodeEvaluator.evaluate(
            nodeConverter.convertNodeToNodeV2(data),
            userDefinedFunctions,
            variableNameToValueMap,
            true
        )
        val rightValue = numericValueRetriever.retrieveNumericValue(
            evaluatedData,
            2,
            FunctionNameConstants.GREATER
        )
        val result = leftValue > rightValue
        return nodeGenerator.generateAtomNode(result)
    }
}