package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.datamodels.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class NodeParserTest {
    private val expressionListNodeParser = Mockito.mock(ExpressionListNodeParser::class.java)
    private val nodeParser = NodeParser(
        expressionListNodeParser
    )

    @Test
    fun parseIntoNodeTest() {
        val headToken = Mockito.mock(Token::class.java)
        val tokens = listOf(headToken)
        Mockito.`when`(headToken.tokenKind).thenReturn(TokenKind.OPEN_TOKEN)

        val result = Mockito.mock(ParserResult::class.java)
        Mockito.`when`(expressionListNodeParser.parseExpressionListNode(tokens, 0)).thenReturn(result)

        val resultingNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(result.resultingNode).thenReturn(resultingNode)

        val expected = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(resultingNode.children).thenReturn(listOf(expected))

        val actual = nodeParser.parseIntoNode(tokens)
        Assertions.assertEquals(expected, actual)
    }
}