package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever

class TimesFunction (
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val nodeEvaluator: NodeEvaluator,
    private val numericValueRetriever: NumericValueRetriever,
    private val listValueRetriever: ListValueRetriever,
    private val nodeGenerator: NodeGenerator,
    private val nodeConverter: NodeConverter
): LispFunction {

    override fun evaluateLispFunction(
        params: Node,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, Node>
    ): Node {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.TIMES,
            FunctionLengthConstants.THREE,
            params
        )
        val evaluatedAddress = nodeEvaluator.evaluate(
            nodeConverter.convertNodeToNodeV2(params),
            userDefinedFunctions,
            variableNameToValueMap,
            true
        )
        val leftValue = numericValueRetriever.retrieveNumericValue(
            evaluatedAddress,
            1,
            FunctionNameConstants.TIMES
        )
        val expressionNodeParams = listValueRetriever.retrieveListValue(
            params,
            FunctionNameConstants.TIMES,
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
            FunctionNameConstants.TIMES
        )
        val result = leftValue * rightValue
        return nodeGenerator.generateAtomNode(result)
    }
}