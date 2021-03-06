package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.asserter.AtomRootNodeAsserter
import com.soapdogg.lispInterpreter.datamodels.*

class ProgramEvaluator(
    private val atomRootNodeAsserter: AtomRootNodeAsserter,
    private val rootNodeEvaluator: RootNodeEvaluator
) {
    fun evaluatePostOrder(
        rootNodes: List<NodeV2>,
        userDefinedFunctions: Map<String, UserDefinedFunction>
    ): List<NodeV2> {
        return rootNodes.map {
            if (it is AtomNode) {
                atomRootNodeAsserter.assertAtomRootNode(it)
                return@map it
            }
            rootNodeEvaluator.evaluate(
                it as ExpressionListNode,
                userDefinedFunctions,
                com.soapdogg.lispInterpreter.datamodels.Stack(),
                com.soapdogg.lispInterpreter.datamodels.Stack()
            )
        }
    }
}