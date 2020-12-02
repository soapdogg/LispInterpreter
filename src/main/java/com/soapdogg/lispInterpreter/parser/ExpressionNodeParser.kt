package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.ParserResult
import com.soapdogg.lispInterpreter.datamodels.Token
import com.soapdogg.lispInterpreter.datamodels.TokenKind
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import java.util.*

class ExpressionNodeParser (
  private val nodeGenerator: NodeGenerator,
  private val expressionNodeFinisher: ExpressionNodeFinisher,
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
            val addressParserResult: ParserResult
            val isOpen = currentToken.tokenKind === TokenKind.OPEN_TOKEN
            addressParserResult = if (isOpen) {
                val t = parseExpressionNode(tokens.subList(1, tokens.size))
                expressionNodeFinisher.finishParsingExpressionNode(t)
            } else {
                atomNodeParser.parseAtomNode(tokens)
            }
            val dataParserResult = parseExpressionNode(addressParserResult.remainingTokens)
            val addressResultingNode = addressParserResult.resultingNode
            val result = nodeGenerator.generateExpressionNode(
                addressResultingNode,
                dataParserResult.resultingNode
            )
            parserResultBuilder.buildParserResult(
                result,
                dataParserResult.remainingTokens
            )
        }
    }
}