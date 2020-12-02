package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.datamodels.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class NodeParserTest {
    private val expressionNodeParser = Mockito.mock(ExpressionNodeParser::class.java)
    private val atomNodeParser = Mockito.mock(AtomNodeParser::class.java)
    private val nodeParser = NodeParser(
        expressionNodeParser,
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

        val actual = nodeParser.parseIntoNode(tokens)
        Assertions.assertEquals(resultingNode, actual)
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
        Mockito.verifyNoInteractions(expressionNodeParser)
    }
}