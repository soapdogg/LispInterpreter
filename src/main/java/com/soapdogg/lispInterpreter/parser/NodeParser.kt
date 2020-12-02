package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.Token

class NodeParser (
  private val expressionListNodeParser: ExpressionListNodeParser,
  private val nodeConverter: NodeConverter
) {

    fun parseIntoNode(
        tokens: List<Token>
    ): Node {
        val expressionListNode = expressionListNodeParser.parseExpressionListNode(tokens, 0)
        return nodeConverter.convertNodeV2ToNode(expressionListNode.resultingNode)
    }
}