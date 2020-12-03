package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.Token

class NodeParser (
  private val expressionListNodeParser: ExpressionListNodeParser
) {

    fun parseIntoNode(
        tokens: List<Token>
    ): NodeV2 {
        return expressionListNodeParser.parseExpressionListNode(tokens, 0).resultingNode.children[0]
    }
}