package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.asserter.TokenKindAsserter
import com.soapdogg.lispInterpreter.datamodels.ParserResult
import com.soapdogg.lispInterpreter.datamodels.Token
import com.soapdogg.lispInterpreter.datamodels.TokenKind
import java.util.Optional

class NodeParser (
  private val tokenKindAsserter: TokenKindAsserter,
  private val expressionNodeParser: ExpressionNodeParser,
  private val expressionNodeFinisher: ExpressionNodeFinisher,
  private val atomNodeParser: AtomNodeParser
) {

    fun parseIntoNode(
        tokens: List<Token>
    ): ParserResult {
        val optionalToken = Optional.ofNullable(tokens[0])
        val token = tokenKindAsserter.assertTokenIsNotNull(optionalToken)
        tokenKindAsserter.assertTokenIsAtomOrOpen(token)
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