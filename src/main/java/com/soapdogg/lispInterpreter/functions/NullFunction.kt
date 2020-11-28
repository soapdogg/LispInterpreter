package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.comparator.NodeValueComparator
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.AtomicValueRetriever

class NullFunction(
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val nodeEvaluator: NodeEvaluator,
    private val expressionNodeDeterminer: ExpressionNodeDeterminer,
    private val atomicValueRetriever: AtomicValueRetriever,
    private val nodeValueComparator: NodeValueComparator,
    private val nodeGenerator: NodeGenerator
) : LispFunction {

    override fun evaluateLispFunction(
        params: Node,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, Node>
    ): Node {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.NULL,
            FunctionLengthConstants.TWO,
            params
        )
        val evaluatedResult = nodeEvaluator.evaluate(
            params,
            userDefinedFunctions,
            variableNameToValueMap,
            true
        )
        if (expressionNodeDeterminer.isExpressionNode(evaluatedResult)) {
            return nodeGenerator.generateAtomNode(false)
        }
        val value = atomicValueRetriever.retrieveAtomicValue(
            evaluatedResult,
            1,
            FunctionNameConstants.NULL
        )
        val result = nodeValueComparator.equalsNil(value)
        return nodeGenerator.generateAtomNode(result)
    }
}