package com.soapdogg.lispInterpreter.determiner

import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node

class ExpressionNodeDeterminer {
    fun isExpressionNode(node: Node): Boolean {
        return node is ExpressionNode
    }
}