package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.asserter.AtomRootNodeAsserter
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.Token
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
        stack: Stack<Token>
    ): Stack<Token> {
        return stackEvaluator.evaluate(stack)
    }
}