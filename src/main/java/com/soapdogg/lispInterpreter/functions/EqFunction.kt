package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.AtomicValueRetriever
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever

class EqFunction (
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val nodeEvaluator: NodeEvaluator,
    private val atomicValueRetriever: AtomicValueRetriever,
    private val listValueRetriever: ListValueRetriever,
    private val nodeGenerator: NodeGenerator
): LispFunction {

    override fun evaluateLispFunction(
        params: Node,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, Node>
    ): Node {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.EQ,
            FunctionLengthConstants.THREE,
            params
        )
        val evaluatedAddress = nodeEvaluator.evaluate(
            params,
            userDefinedFunctions,
            variableNameToValueMap,
            true
        )
        val leftValue = atomicValueRetriever.retrieveAtomicValue(
            evaluatedAddress,
            1,
            FunctionNameConstants.EQ
        )
        val expressionNodeParams = listValueRetriever.retrieveListValue(
            params,
            FunctionNameConstants.EQ,
            variableNameToValueMap
        )
        val data = expressionNodeParams.data
        val evaluatedData = nodeEvaluator.evaluate(
            data,
            userDefinedFunctions,
            variableNameToValueMap,
            true
        )
        val rightValue = atomicValueRetriever.retrieveAtomicValue(
            evaluatedData,
            2,
            FunctionNameConstants.EQ
        )
        val result = leftValue == rightValue
        return nodeGenerator.generateAtomNode(result)
    }
}