package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.generator.ParserResultGenerator

class ExpressionListNodeParser(
    private val nodeGenerator: NodeGenerator,
    private val parserResultGenerator: ParserResultGenerator
) {

    fun parseExpressionListNode(
        tokens: List<Token>,
        startingPoint: Int
    ): ParserResult {
        return if (tokens.size == 1) {
            parserResultGenerator.generateParserResultForAtomNode(tokens[0].value)
        }
        else if (tokens[startingPoint + 1].tokenKind == TokenKind.CLOSE_TOKEN) {
            parserResultGenerator.generateParserResultForNilAtomNode(startingPoint)
        } else {
            val result = ArrayList<NodeV2>()
            var i = startingPoint + 1
            loop@ while (i < tokens.size) {
                val token = tokens[i]
                when (token.tokenKind) {
                    TokenKind.OPEN_TOKEN -> {
                        val nodeV2 = parseExpressionListNode(tokens, i)
                        result += nodeV2.resultingNode
                        i = nodeV2.nextIndex
                    }
                    TokenKind.CLOSE_TOKEN -> {
                        ++i
                        break@loop
                    }
                    else -> {
                        val nodeV2 = nodeGenerator.generateAtomNode(token.value)
                        result += nodeV2
                        ++i
                    }
                }
            }
            parserResultGenerator.generateParserResultForExpressionListNode(
                result,
                i
            )
        }
    }
}