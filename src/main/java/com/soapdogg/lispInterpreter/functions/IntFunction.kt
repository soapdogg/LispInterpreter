package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.generator.NodeGenerator

class IntFunction (
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val nodeEvaluator: NodeEvaluator,
    private val numericStringDeterminer: NumericStringDeterminer,
    private val nodeGenerator: NodeGenerator
): LispFunctionV2 {

    override fun evaluateLispFunction(
        params: ExpressionListNode,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, NodeV2>
    ): NodeV2 {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.INT,
            FunctionLengthConstants.TWO,
            params
        )
        val evaluatedResult = nodeEvaluator.evaluateV2(
            params.children[1],
            userDefinedFunctions,
            variableNameToValueMap,
            true
        )
        if (evaluatedResult is ExpressionListNode) {
            return nodeGenerator.generateAtomNode(false)
        }
        val value = (evaluatedResult as AtomNode).value
        val result = numericStringDeterminer.isStringNumeric(value)
        return nodeGenerator.generateAtomNode(result)
    }
}