package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.datamodels.ParserResult

class ExpressionNodeFinisher(
    private val parserResultBuilder: ParserResultBuilder
) {

    fun finishParsingExpressionNode(
        result: ParserResult
    ): ParserResult {
        return parserResultBuilder.buildParserResult(
            result.resultingNode,
            result.remainingTokens.subList(1, result.remainingTokens.size)
        )
    }
}