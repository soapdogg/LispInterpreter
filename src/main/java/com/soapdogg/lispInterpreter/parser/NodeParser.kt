package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.Token
import com.soapdogg.lispInterpreter.datamodels.TokenKind

class NodeParser (
  private val expressionNodeParser: ExpressionNodeParser,
  private val atomNodeParser: AtomNodeParser
) {

    fun parseIntoNode(
        tokens: List<Token>
    ): Node {
        val token = tokens[0]
        val currentTokenKind = token.tokenKind
        val isOpen = currentTokenKind === TokenKind.OPEN_TOKEN
        return if (isOpen) {
            val remaining = tokens.subList(1, tokens.size)
            expressionNodeParser.parseExpressionNode(remaining).resultingNode
        } else {
            atomNodeParser.parseAtomNode(
                tokens[0]
            )
        }
    }
}