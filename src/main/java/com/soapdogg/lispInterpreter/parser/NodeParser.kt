package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.datamodels.ParserResult
import com.soapdogg.lispInterpreter.datamodels.Token
import com.soapdogg.lispInterpreter.datamodels.TokenKind

class NodeParser (
  private val expressionNodeParser: ExpressionNodeParser,
  private val expressionNodeFinisher: ExpressionNodeFinisher,
  private val atomNodeParser: AtomNodeParser
) {

    fun parseIntoNode(
        tokens: List<Token>
    ): ParserResult {
        val token = tokens[0]
        val currentTokenKind = token.tokenKind
        val isOpen = currentTokenKind === TokenKind.OPEN_TOKEN
        return if (isOpen) {
            val remaining = tokens.subList(1, tokens.size)
            val result = expressionNodeParser.parseExpressionNode(remaining)
            expressionNodeFinisher.finishParsingExpressionNode(result)
        } else {
            atomNodeParser.parseAtomNode(
                tokens
            )
        }
    }
}