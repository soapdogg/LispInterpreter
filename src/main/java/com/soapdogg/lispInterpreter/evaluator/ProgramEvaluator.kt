package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.asserter.AtomRootNodeAsserter
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer
import java.util.*

class ProgramEvaluator(
    private val expressionNodeDeterminer: ExpressionNodeDeterminer,
    private val atomRootNodeAsserter: AtomRootNodeAsserter,
    private val nodeEvaluator: NodeEvaluator
) {
    fun evaluate(
        rootNodes: List<Node>,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, Node>
    ): List<Node> {
        return rootNodes.map {
            val isNotList = !expressionNodeDeterminer.isExpressionNode(it)
            if (isNotList) {
                val atomNode = it as AtomNode
                atomRootNodeAsserter.assertAtomRootNode(atomNode)
            }
            nodeEvaluator.evaluate(
                it,
                userDefinedFunctions,
                variableNameToValueMap,
                false
            )
        }
    }
}