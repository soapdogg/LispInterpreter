package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.exceptions.NotAListException

class CondFunctionParameterAsserter(
    private val nodeValueComparator: NodeValueComparator
) {

    fun assertCondFunctionParameters(
        params: List<NodeV2>
    ): List<ExpressionListNode> {
        return params.map {
            if (it is AtomNode) {
                if (!nodeValueComparator.equalsNil(it.value)) {
                    throw NotAListException("Error! COND parameter: ${it.value} is not a list!${'\n'}")
                }
            }
            it
        }.filterIsInstance<ExpressionListNode>()
    }
}