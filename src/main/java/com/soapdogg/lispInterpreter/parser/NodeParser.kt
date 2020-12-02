package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.Token
import com.soapdogg.lispInterpreter.datamodels.TokenKind

class NodeParser (
  private val expressionListNodeParser: ExpressionListNodeParser,
  private val nodeConverter: NodeConverter,
  private val atomNodeParser: AtomNodeParser
) {

    fun parseIntoNode(
        tokens: List<Token>
    ): Node {
        val token = tokens[0]
        val currentTokenKind = token.tokenKind
        val isOpen = currentTokenKind === TokenKind.OPEN_TOKEN
        return if (isOpen) {
            val expressionListNode = expressionListNodeParser.parseExpressionListNode(tokens, 0)
            return nodeConverter.convertNodeV2ToNode(expressionListNode.resultingNode)
        } else {
            atomNodeParser.parseAtomNode(
                tokens[0]
            )
        }
    }
}