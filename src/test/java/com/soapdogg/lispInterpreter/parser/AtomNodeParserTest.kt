package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ParserResult
import com.soapdogg.lispInterpreter.datamodels.Token
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class AtomNodeParserTest {
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)
    private val parserResultBuilder = Mockito.mock(ParserResultBuilder::class.java)
    private val atomNodeParser = AtomNodeParser(
        nodeGenerator,
        parserResultBuilder
    )

    @Test
    fun atomNodeParserTest() {
        val value = "value"
        val headToken = Mockito.mock(Token::class.java)
        Mockito.`when`(headToken.value).thenReturn(value)

        val atomNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(value)).thenReturn(atomNode)

        val tokens = listOf(headToken)
        val expected = Mockito.mock(ParserResult::class.java)
        Mockito.`when`(
            parserResultBuilder.buildParserResult(
                atomNode, emptyList())
        ).thenReturn(expected)

        val actual = atomNodeParser.parseAtomNode(tokens)
        Assertions.assertEquals(expected, actual)
    }
}