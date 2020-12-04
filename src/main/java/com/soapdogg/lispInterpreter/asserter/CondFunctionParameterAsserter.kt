package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.exceptions.NotAListException

class CondFunctionParameterAsserter(
    private val nodeValueComparator: NodeValueComparator,
    private val functionLengthAsserter: FunctionLengthAsserter
) {

    fun assertCondFunctionParameters(
        params: List<NodeV2>
    ): List<ExpressionListNode> {
        return params.map {
            if (it is AtomNode) {
                if (!nodeValueComparator.equalsNil(it.value)) {
                    throw NotAListException("Error! COND parameter: ${it.value} is not a list!${'\n'}")
                }
            } else {
                functionLengthAsserter.assertLengthIsAsExpected(
                    FunctionNameConstants.COND,
                    FunctionLengthConstants.TWO,
                    it
                )
            }
            it
        }.filterIsInstance<ExpressionListNode>()
    }
}