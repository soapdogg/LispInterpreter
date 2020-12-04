package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.asserter.CondFunctionParameterAsserter
import com.soapdogg.lispInterpreter.comparator.NodeValueComparator
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.exceptions.NotAListException

class CondFunction(
    private val condFunctionParameterAsserter: CondFunctionParameterAsserter,
    private val nodeEvaluator: NodeEvaluator,
    private val nodeValueComparator: NodeValueComparator
): LispFunctionV2 {

    override fun evaluateLispFunction(
        params: ExpressionListNode,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, NodeV2>
    ): NodeV2 {
        val condParams = params.children.subList(1, params.children.size)
        val condExpressionParams = condFunctionParameterAsserter.assertCondFunctionParameters(
            condParams
        )
        condExpressionParams.forEach {
            val evaluatedNode = nodeEvaluator.evaluateV2(
                it.children[0],
                userDefinedFunctions,
                variableNameToValueMap
            )
            if (evaluatedNode is AtomNode && !nodeValueComparator.equalsNil(evaluatedNode.value)) {
                return nodeEvaluator.evaluateV2(
                    it.children[1],
                    userDefinedFunctions,
                    variableNameToValueMap
                )
            }
        }
        throw NotAListException("Error! None of the conditions in the COND function evaluated to true.\n")
    }
}