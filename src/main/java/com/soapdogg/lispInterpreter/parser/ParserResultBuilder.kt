package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.ParserResult
import com.soapdogg.lispInterpreter.datamodels.Token

class ParserResultBuilder {
    fun buildParserResult(
        resultingNode: Node,
        remainingTokens: List<Token>
    ): ParserResult {
        return ParserResult(
            resultingNode,
            remainingTokens
        )
    }
}