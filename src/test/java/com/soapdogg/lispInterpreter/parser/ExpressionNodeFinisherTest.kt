package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.ParserResult
import com.soapdogg.lispInterpreter.datamodels.Token
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ExpressionNodeFinisherTest {
    private val parserResultBuilder = Mockito.mock(ParserResultBuilder::class.java)
    private val expressionNodeFinisher = ExpressionNodeFinisher(
        parserResultBuilder
    )

    @Test
    fun finishParsingExpressionNodeTest() {
        val result = Mockito.mock(ParserResult::class.java)
        val resultingNode = Mockito.mock(Node::class.java)
        Mockito.`when`(result.resultingNode).thenReturn(resultingNode)

        val headToken = Mockito.mock(Token::class.java)
        val remainingTokens = listOf(headToken)
        Mockito.`when`(result.remainingTokens).thenReturn(remainingTokens)

        val expected = Mockito.mock(ParserResult::class.java)
        Mockito.`when`(
            parserResultBuilder.buildParserResult(
                resultingNode,
                listOf()
            )
        ).thenReturn(expected)

        val actual = expressionNodeFinisher.finishParsingExpressionNode(result)
        Assertions.assertEquals(expected, actual)
    }
}