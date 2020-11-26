package com.soapdogg.lispInterpreter.determiner

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node

class FunctionLengthDeterminer {
    fun determineFunctionLength(node: Node): Int {
        return if (node is AtomNode) {
            if (node.value == ReservedValuesConstants.NIL) 0 else 1
        } else {
            val expressionNode = node as ExpressionNode
            determineFunctionLength(expressionNode.data) + 1
        }
    }
}