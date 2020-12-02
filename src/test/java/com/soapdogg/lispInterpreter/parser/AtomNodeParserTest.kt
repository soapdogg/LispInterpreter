package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ParserResult
import com.soapdogg.lispInterpreter.datamodels.Token
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class AtomNodeParserTest {
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)
    private val nodeConverter = Mockito.mock(NodeConverter::class.java)
    private val atomNodeParser = AtomNodeParser(
        nodeGenerator,
        nodeConverter
    )

    @Test
    fun atomNodeParserTest() {
        val value = "value"
        val headToken = Mockito.mock(Token::class.java)
        Mockito.`when`(headToken.value).thenReturn(value)

        val atomNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(value)).thenReturn(atomNode)

        Mockito.`when`(nodeConverter.convertNodeV2ToNode(atomNode)).thenReturn(atomNode)

        val actual = atomNodeParser.parseAtomNode(headToken)
        Assertions.assertEquals(atomNode, actual)
    }
}