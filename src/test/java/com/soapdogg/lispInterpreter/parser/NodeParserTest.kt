package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.datamodels.ParserResult
import com.soapdogg.lispInterpreter.datamodels.Token
import com.soapdogg.lispInterpreter.datamodels.TokenKind
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class NodeParserTest {
    private val expressionNodeParser = Mockito.mock(ExpressionNodeParser::class.java)
    private val expressionNodeFinisher = Mockito.mock(ExpressionNodeFinisher::class.java)
    private val atomNodeParser = Mockito.mock(AtomNodeParser::class.java)
    private val nodeParser = NodeParser(
        expressionNodeParser,
        expressionNodeFinisher,
        atomNodeParser
    )

    @Test
    fun parseIntoExpressionNodeTest() {
        val headToken = Mockito.mock(Token::class.java)
        val tokens = listOf(headToken)
        Mockito.`when`(headToken.tokenKind).thenReturn(TokenKind.OPEN_TOKEN)

        val result = Mockito.mock(ParserResult::class.java)
        Mockito.`when`(expressionNodeParser.parseExpressionNode(LinkedList())).thenReturn(result)

        val expected = Mockito.mock(ParserResult::class.java)
        Mockito.`when`(expressionNodeFinisher.finishParsingExpressionNode(result)).thenReturn(expected)

        val actual = nodeParser.parseIntoNode(tokens)
        Assertions.assertEquals(expected, actual)
        Mockito.verifyNoInteractions(atomNodeParser)
    }

    @Test
    fun parseIntoAtomNodeTest() {
        val headToken = Mockito.mock(Token::class.java)
        val tokens = listOf(headToken)
        Mockito.`when`(headToken.tokenKind).thenReturn(TokenKind.NUMERIC_TOKEN)

        val expected = Mockito.mock(ParserResult::class.java)
        Mockito.`when`(
            atomNodeParser.parseAtomNode(tokens)
        ).thenReturn(expected)

        val actual = nodeParser.parseIntoNode(tokens)
        Assertions.assertEquals(expected, actual)
        Mockito.verifyNoInteractions(expressionNodeParser)
    }
}