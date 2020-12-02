package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.Token
import com.soapdogg.lispInterpreter.datamodels.TokenKind
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ExpressionListNodeParserTest {

    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)
    private val expressionListNodeParser = ExpressionListNodeParser(nodeGenerator)

    @Test
    fun parseExpressionListNodeTest() {
        val startingPoint = 0

        val openToken = Mockito.mock(Token::class.java)
        Mockito.`when`(openToken.tokenKind).thenReturn(TokenKind.OPEN_TOKEN)

        val literalToken = Mockito.mock(Token::class.java)
        Mockito.`when`(literalToken.tokenKind).thenReturn(TokenKind.LITERAL_TOKEN)

        val value = "value"
        Mockito.`when`(literalToken.value).thenReturn(value)

        val closeToken = Mockito.mock(Token::class.java)
        Mockito.`when`(closeToken.tokenKind).thenReturn(TokenKind.CLOSE_TOKEN)

        val tokens = listOf(
            openToken,
            literalToken,
            closeToken
        )

        val valueNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(value)).thenReturn(valueNode)

        val nilNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(ReservedValuesConstants.NIL)).thenReturn(nilNode)

        val expressionListNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateExpressionListNode(
                listOf(
                    valueNode,
                    nilNode
                )
            )
        ).thenReturn(expressionListNode)

        val rootNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateExpressionListNode(
                listOf(
                    expressionListNode
                )
            )
        ).thenReturn(rootNode)

        val actual = expressionListNodeParser.parseExpressionListNode(
            tokens,
            startingPoint
        )

        Assertions.assertEquals(rootNode, actual.resultingNode)
        Assertions.assertEquals(3, actual.nextIndex)
    }
}