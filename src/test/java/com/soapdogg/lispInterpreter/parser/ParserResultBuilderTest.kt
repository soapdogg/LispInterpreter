package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.Token
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ParserResultBuilderTest {
    private val parserResultBuilder = ParserResultBuilder()

    @Test
    fun buildParserResultTest() {
        val resultingNode = Mockito.mock(Node::class.java)
        val remainingTokens: List<Token> = emptyList()
        val (resultingNode1, remainingTokens1) = parserResultBuilder.buildParserResult(
            resultingNode,
            remainingTokens
        )
        Assertions.assertEquals(resultingNode, resultingNode1)
        Assertions.assertEquals(remainingTokens, remainingTokens1)
    }
}