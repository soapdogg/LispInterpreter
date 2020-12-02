package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.AtomicValueRetriever

class IntFunction (
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val nodeEvaluator: NodeEvaluator,
    private val atomicValueRetriever: AtomicValueRetriever,
    private val numericStringDeterminer: NumericStringDeterminer, 
    private val nodeGenerator: NodeGenerator
): LispFunction {

    override fun evaluateLispFunction(
        params: Node,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, Node>
    ): Node {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.INT,
            FunctionLengthConstants.TWO,
            params
        )
        val evaluatedResult = nodeEvaluator.evaluate(
            params,
            userDefinedFunctions,
            variableNameToValueMap,
            true
        )
        if (evaluatedResult is ExpressionNode) {
            return nodeGenerator.generateAtomNode(false)
        }
        val value = atomicValueRetriever.retrieveAtomicValue(
            evaluatedResult,
            1,
            FunctionNameConstants.INT
        )
        val result = numericStringDeterminer.isStringNumeric(value)
        return nodeGenerator.generateAtomNode(result)
    }
}