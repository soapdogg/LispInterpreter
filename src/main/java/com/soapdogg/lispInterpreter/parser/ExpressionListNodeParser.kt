package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.generator.NodeGenerator

class ExpressionListNodeParser(
    private val nodeGenerator: NodeGenerator
) {

    fun parseExpressionListNode(
        tokens: List<Token>,
        startingPoint: Int
    ): ParserResultV2 {
        if (tokens[startingPoint + 1].tokenKind == TokenKind.CLOSE_TOKEN) {
            return ParserResultV2(nodeGenerator.generateAtomNode(ReservedValuesConstants.NIL), startingPoint + 2)
        }
        val result = ArrayList<NodeV2>()
        var i = startingPoint + 1
        while(i < tokens.size) {
            val token = tokens[i]
            if (token.tokenKind == TokenKind.OPEN_TOKEN) {
                val nodeV2 = parseExpressionListNode(tokens, i)
                i = nodeV2.nextIndex
                result.add(nodeV2.resultingNode)
            } else if (token.tokenKind == TokenKind.CLOSE_TOKEN) {
                ++i
                break
            } else {
                val nodeV2 = nodeGenerator.generateAtomNode(token.value)
                result.add(nodeV2)
                ++i
            }
        }
        return ParserResultV2(ExpressionListNode(result), i)
    }
}