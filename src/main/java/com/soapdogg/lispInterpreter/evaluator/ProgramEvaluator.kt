package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.asserter.AtomRootNodeAsserter
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import java.util.*

class ProgramEvaluator(
    private val atomRootNodeAsserter: AtomRootNodeAsserter,
    private val nodeEvaluator: NodeEvaluator,
    private val stackEvaluator: StackEvaluator
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

    fun evaluateStacks(
        stacks: List<Stack<AtomNode>>
    ):List<Stack<AtomNode>> {
        return stacks.map {
            if (it.size == 1) {
                atomRootNodeAsserter.assertAtomRootNode(it[0])
                return@map it
            }
            stackEvaluator.evaluate(it)
        }
    }
}