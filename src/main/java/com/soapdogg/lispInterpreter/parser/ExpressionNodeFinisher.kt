package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.asserter.TokenKindAsserter
import com.soapdogg.lispInterpreter.datamodels.ParserResult
import com.soapdogg.lispInterpreter.datamodels.Token
import java.util.*

class ExpressionNodeFinisher(
    private val tokenKindAsserter: TokenKindAsserter,
    private val parserResultBuilder: ParserResultBuilder
) {

    fun finishParsingExpressionNode(
        result: ParserResult
    ): ParserResult {
        val remainingTokens = result.remainingTokens
        val closeTokenOptional: Optional<Token> = Optional.ofNullable(remainingTokens[0])
        val closeToken = tokenKindAsserter.assertTokenIsNotNull(closeTokenOptional)
        tokenKindAsserter.assertTokenIsClose(closeToken)
        return parserResultBuilder.buildParserResult(
            result.resultingNode,
            result.remainingTokens.subList(1, result.remainingTokens.size)
        )
    }
}