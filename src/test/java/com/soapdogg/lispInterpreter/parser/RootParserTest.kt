package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.ParserResult
import com.soapdogg.lispInterpreter.datamodels.Token
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class RootParserTest {
    private val nodeParser = Mockito.mock(NodeParser::class.java)
    private val rootParser = RootParser(nodeParser)

    @Test
    fun rootParserTest() {
        val headToken = Mockito.mock(Token::class.java)
        val tokens = listOf(headToken)

        val resultingNode = Mockito.mock(Node::class.java)
        val remainingTokens: List<Token> = emptyList()
        val parserResult = Mockito.mock(ParserResult::class.java)
        Mockito.`when`(parserResult.resultingNode).thenReturn(resultingNode)
        Mockito.`when`(parserResult.remainingTokens).thenReturn(remainingTokens)
        Mockito.`when`(nodeParser.parseIntoNode(tokens)).thenReturn(parserResult)

        val actual = rootParser.parse(tokens)
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(resultingNode, actual[0])
    }
}