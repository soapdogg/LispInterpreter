package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever

class CdrFunction (
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val listValueRetriever: ListValueRetriever,
    private val nodeEvaluator: NodeEvaluator,
    private val nodeGenerator: NodeGenerator
): LispFunctionV2 {

    override fun evaluateLispFunction(
        params: ExpressionListNode,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, NodeV2>
    ): NodeV2 {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.CDR,
            FunctionLengthConstants.TWO,
            params
        )
        val evaluatedChild = nodeEvaluator.evaluateV2(
            params.children[1],
            userDefinedFunctions,
            variableNameToValueMap
        )
        val evaluatedChildExpressionList = listValueRetriever.retrieveListValue(
            evaluatedChild,
            FunctionNameConstants.CDR,
            variableNameToValueMap
        )
        if (evaluatedChildExpressionList.children.size == 1) {
            return evaluatedChildExpressionList.children[0]
        }
        if (evaluatedChildExpressionList.children.size == 2) {
            return evaluatedChildExpressionList.children[1]
        }
        return nodeGenerator.generateExpressionListNode(
            evaluatedChildExpressionList.children.subList(1, evaluatedChildExpressionList.children.size)
        )
    }
}