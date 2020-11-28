package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.generator.NodeGenerator

class AtomFunction (
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val nodeEvaluator: NodeEvaluator,
    private val expressionNodeDeterminer: ExpressionNodeDeterminer,
    private val nodeGenerator: NodeGenerator
): LispFunction {

    override fun evaluateLispFunction(
        params: Node,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, Node>
    ): Node {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.ATOM,
            FunctionLengthConstants.TWO,
            params
        )
        val evaluatedResult = nodeEvaluator.evaluate(
            params,
            userDefinedFunctions,
            variableNameToValueMap,
            true
        )
        val result = !expressionNodeDeterminer.isExpressionNode(evaluatedResult)
        return nodeGenerator.generateAtomNode(result)
    }
}