package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class NodeParserTest {
    private val expressionListNodeParser = Mockito.mock(ExpressionListNodeParser::class.java)
    private val nodeConverter = Mockito.mock(NodeConverter::class.java)
    private val atomNodeParser = Mockito.mock(AtomNodeParser::class.java)
    private val nodeParser = NodeParser(
        expressionListNodeParser,
        nodeConverter,
        atomNodeParser
    )

    @Test
    fun parseIntoExpressionNodeTest() {
        val headToken = Mockito.mock(Token::class.java)
        val tokens = listOf(headToken)
        Mockito.`when`(headToken.tokenKind).thenReturn(TokenKind.OPEN_TOKEN)

        val result = Mockito.mock(ParserResultV2::class.java)
        Mockito.`when`(expressionListNodeParser.parseExpressionListNode(tokens, 0)).thenReturn(result)

        val resultingNode = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(result.resultingNode).thenReturn(resultingNode)

        val expected = Mockito.mock(Node::class.java)
        Mockito.`when`(nodeConverter.convertNodeV2ToNode(resultingNode)).thenReturn(expected)

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

        val actual = nodeParser.parseIntoNode(tokens)
        Assertions.assertEquals(t, actual)
        Mockito.verifyNoInteractions(expressionListNodeParser)
        Mockito.verifyNoInteractions(nodeConverter)
    }
}