package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever

class MinusFunction (
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
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.MINUS,
            FunctionLengthConstants.THREE,
            params
        )
        val evaluatedAddress = nodeEvaluator.evaluate(
            params,
            userDefinedFunctions,
            variableNameToValueMap,
            true
        )
        val leftValue = numericValueRetriever.retrieveNumericValue(
            evaluatedAddress,
            1,
            FunctionNameConstants.MINUS
        )
        val expressionNodeParams = listValueRetriever.retrieveListValue(
            params,
            FunctionNameConstants.MINUS,
            variableNameToValueMap
        )
        val data = expressionNodeParams.data
        val evaluatedData = nodeEvaluator.evaluate(
            data,
            userDefinedFunctions,
            variableNameToValueMap,
            true
        )
        val rightValue = numericValueRetriever.retrieveNumericValue(
            evaluatedData,
            2,
            FunctionNameConstants.MINUS
        )
        val result = leftValue - rightValue
        return nodeGenerator.generateAtomNode(result)
    }
}