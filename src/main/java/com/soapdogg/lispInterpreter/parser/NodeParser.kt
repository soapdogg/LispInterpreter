package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.datamodels.ParserResult
import com.soapdogg.lispInterpreter.datamodels.Token
import com.soapdogg.lispInterpreter.datamodels.TokenKind

class NodeParser (
  private val expressionNodeParser: ExpressionNodeParser,
  private val parserResultBuilder: ParserResultBuilder,
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
            parserResultBuilder.buildParserResult(
                result.resultingNode,
                result.remainingTokens.subList(1, result.remainingTokens.size)
            )
        } else {
            atomNodeParser.parseAtomNode(
                tokens
            )
        }
    }
}