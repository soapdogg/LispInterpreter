package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.ParserResult
import com.soapdogg.lispInterpreter.datamodels.Token
import com.soapdogg.lispInterpreter.datamodels.TokenKind
import com.soapdogg.lispInterpreter.generator.NodeGenerator

class ExpressionNodeParser (
  private val nodeGenerator: NodeGenerator,
  private val atomNodeParser: AtomNodeParser,
  private val parserResultBuilder: ParserResultBuilder
) {
    
    fun parseExpressionNode(
        tokens: List<Token>
    ): ParserResult {
        val currentToken = tokens[0]
        val isClose = currentToken.tokenKind === TokenKind.CLOSE_TOKEN
        return if (isClose) {
            val result: Node = nodeGenerator.generateAtomNode(ReservedValuesConstants.NIL)
            parserResultBuilder.buildParserResult(
                result,
                tokens
            )
        } else {
            val isOpen = currentToken.tokenKind === TokenKind.OPEN_TOKEN
            val addressParserResult = if (isOpen) {
                val t = parseExpressionNode(tokens.subList(1, tokens.size))
                parserResultBuilder.buildParserResult(
                    t.resultingNode,
                    t.remainingTokens.subList(1, t.remainingTokens.size)
                )
            } else {
                val t = atomNodeParser.parseAtomNode(tokens[0])
                parserResultBuilder.buildParserResult(
                    t,
                    tokens.subList(1, tokens.size)
                )
            }
            val dataParserResult = parseExpressionNode(addressParserResult.remainingTokens)
            val result = nodeGenerator.generateExpressionNode(
                addressParserResult.resultingNode,
                dataParserResult.resultingNode
            )
            parserResultBuilder.buildParserResult(
                result,
                dataParserResult.remainingTokens
            )
        }
    }
}