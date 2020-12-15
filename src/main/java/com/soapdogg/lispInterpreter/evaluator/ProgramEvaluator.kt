package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.asserter.AtomRootNodeAsserter
import com.soapdogg.lispInterpreter.datamodels.*

class ProgramEvaluator(
    private val atomRootNodeAsserter: AtomRootNodeAsserter,
    private val nodeEvaluator: NodeEvaluator,
    private val nodeEvaluatorIterative: NodeEvaluatorIterative
) {
    fun evaluate(
        rootNodes: List<NodeV2>,
        userDefinedFunctions: Map<String, UserDefinedFunction>,
        variableNameToValueMap: Map<String, NodeV2>
    ): List<NodeV2> {
        return rootNodes.map {
            if (it is AtomNode) {
                atomRootNodeAsserter.assertAtomRootNode(it)
            }
            nodeEvaluator.evaluateV2(
                listOf(it),
                userDefinedFunctions,
                variableNameToValueMap
            )[0]
        }
    }

    fun evaluatePostOrder(
        rootNodes: List<NodeV2>
    ): List<NodeV2> {
        return rootNodes.map {
            if (it is AtomNode) {
                atomRootNodeAsserter.assertAtomRootNode(it)
            }
            nodeEvaluatorIterative.evaluate(
                it as ExpressionListNode
            )
        }
    }
}