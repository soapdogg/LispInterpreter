package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.datamodels.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class NodeParserTest {
    private val expressionNodeParser = Mockito.mock(ExpressionNodeParser::class.java)
    private val parserResultBuilder = Mockito.mock(ParserResultBuilder::class.java)
    private val atomNodeParser = Mockito.mock(AtomNodeParser::class.java)
    private val nodeParser = NodeParser(
        expressionNodeParser,
        parserResultBuilder,
        atomNodeParser
    )

    @Test
    fun parseIntoExpressionNodeTest() {
        val headToken = Mockito.mock(Token::class.java)
        val tokens = listOf(headToken)
        Mockito.`when`(headToken.tokenKind).thenReturn(TokenKind.OPEN_TOKEN)

        val result = Mockito.mock(ParserResult::class.java)
        Mockito.`when`(expressionNodeParser.parseExpressionNode(LinkedList())).thenReturn(result)

        val resultingNode = Mockito.mock(Node::class.java)
        Mockito.`when`(result.resultingNode).thenReturn(resultingNode)

        Mockito.`when`(result.remainingTokens).thenReturn(tokens)

        val expected = Mockito.mock(ParserResult::class.java)
        Mockito.`when`(
            parserResultBuilder.buildParserResult(
                resultingNode,
                emptyList()
            )
        ).thenReturn(expected)

        val actual = nodeParser.parseIntoNode(tokens)
        Assertions.assertEquals(expected, actual)
        Mockito.verifyNoInteractions(atomNodeParser)
    }

    @Test
    fun parseIntoAtomNodeTest() {
        val headToken = Mockito.mock(Token::class.java)
        val tokens = listOf(headToken)
        Mockito.`when`(headToken.tokenKind).thenReturn(TokenKind.NUMERIC_TOKEN)

        val t = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(
            atomNodeParser.parseAtomNode(headToken)
        ).thenReturn(t)

        val expected = Mockito.mock(ParserResult::class.java)
        Mockito.`when`(
            parserResultBuilder.buildParserResult(
                t,
                emptyList()
            )
        ).thenReturn(expected)

        val actual = nodeParser.parseIntoNode(tokens)
        Assertions.assertEquals(expected, actual)
        Mockito.verifyNoInteractions(expressionNodeParser)
    }
}