package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class ExpressionNodeParserTest {
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)
    private val expressionNodeFinisher = Mockito.mock(ExpressionNodeFinisher::class.java)
    private val atomNodeParser = Mockito.mock(AtomNodeParser::class.java)
    private val parserResultBuilder = Mockito.mock(ParserResultBuilder::class.java)
    private val expressionNodeParser = ExpressionNodeParser(
        nodeGenerator,
        expressionNodeFinisher,
        atomNodeParser,
        parserResultBuilder
    )

    @Test
    fun headTokenIsCloseTest() {
        val headToken = Mockito.mock(Token::class.java)
        val tokens = listOf(headToken)
        Mockito.`when`(headToken.tokenKind).thenReturn(TokenKind.CLOSE_TOKEN)
        val result = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(ReservedValuesConstants.NIL)).thenReturn(result)
        val expected = Mockito.mock(ParserResult::class.java)
        Mockito.`when`(
            parserResultBuilder.buildParserResult(
                result,
                tokens
            )
        ).thenReturn(expected)
        val actual = expressionNodeParser.parseExpressionNode(
            tokens
        )
        Assertions.assertEquals(expected, actual)
        Mockito.verifyNoInteractions(atomNodeParser)
    }

    /*@Test
    fun headTokenIsOpenTest() {
        val headToken = Mockito.mock(Token::class.java)
        Mockito.`when`(headToken.tokenKind).thenReturn(TokenKind.OPEN_TOKEN)
        Mockito.`when`(
            tokenKindAsserter.assertTokenIsNotNull(Optional.of(headToken))
        ).thenReturn(headToken)
        val closeToken = Mockito.mock(Token::class.java)
        Mockito.`when`(closeToken.tokenKind).thenReturn(TokenKind.CLOSE_TOKEN)
        val tokens = listOf(
            headToken,
            closeToken
        )
        Mockito.`when`(
            tokenKindAsserter.assertTokenIsNotNull(Optional.of(closeToken))
        ).thenReturn(closeToken)
        val closeTokenResult = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(ReservedValuesConstants.NIL)).thenReturn(closeTokenResult)
        val closeTokenParserResult = Mockito.mock(ParserResult::class.java)
        Mockito.`when`(
            parserResultBuilder.buildParserResult(
                closeTokenResult,
                tokens
            )
        ).thenReturn(closeTokenParserResult)
        val addressParserResult = Mockito.mock(ParserResult::class.java)
        Mockito.`when`(
            expressionNodeFinisher.finishParsingExpressionNode(ArgumentMatchers.any(ParserResult::class.java))
        ).thenReturn(addressParserResult)
        val addressResultingNode = Mockito.mock(Node::class.java)
        Mockito.`when`(
            addressParserResult.resultingNode
        ).thenReturn(addressResultingNode)
        val remainingTokens: MutableList<Token> = ArrayList()
        remainingTokens.add(closeToken)
        Mockito.`when`(
            addressParserResult.remainingTokens
        ).thenReturn(remainingTokens)
        val dataParserResult = Mockito.mock(ParserResult::class.java)
        Mockito.`when`(
            parserResultBuilder.buildParserResult(
                closeTokenResult,
                remainingTokens
            )
        ).thenReturn(dataParserResult)
        val dataResultingNode = Mockito.mock(Node::class.java)
        Mockito.`when`(dataParserResult.resultingNode).thenReturn(dataResultingNode)
        val result = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateExpressionNode(
                addressResultingNode,
                dataResultingNode
            )
        ).thenReturn(result)
        val dataRemainingTokens: List<Token> = LinkedList()
        Mockito.`when`(dataParserResult.remainingTokens).thenReturn(dataRemainingTokens)
        val expected = Mockito.mock(ParserResult::class.java)
        Mockito.`when`(
            parserResultBuilder.buildParserResult(
                result,
                dataRemainingTokens
            )
        ).thenReturn(expected)
        val actual = expressionNodeParser.parseExpressionNode(tokens)
        Assertions.assertEquals(expected, actual)
    }*/

    @Test
    fun headTokenIsNumericTest() {
        val headToken = Mockito.mock(Token::class.java)
        Mockito.`when`(headToken.tokenKind).thenReturn(TokenKind.NUMERIC_TOKEN)
        val closeToken = Mockito.mock(Token::class.java)
        Mockito.`when`(closeToken.tokenKind).thenReturn(TokenKind.CLOSE_TOKEN)
        val tokens = listOf(
            headToken,
            closeToken
        )

        val closeTokenResult = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(ReservedValuesConstants.NIL)).thenReturn(closeTokenResult)
        val closeTokenParserResult = Mockito.mock(ParserResult::class.java)
        Mockito.`when`(
            parserResultBuilder.buildParserResult(
                closeTokenResult,
                tokens
            )
        ).thenReturn(closeTokenParserResult)
        val addressParserResult = Mockito.mock(ParserResult::class.java)
        Mockito.`when`(
            atomNodeParser.parseAtomNode(tokens)
        ).thenReturn(addressParserResult)
        val addressResultingNode = Mockito.mock(Node::class.java)
        Mockito.`when`(
            addressParserResult.resultingNode
        ).thenReturn(addressResultingNode)
        val remainingTokens: MutableList<Token> = ArrayList()
        remainingTokens.add(closeToken)
        Mockito.`when`(
            addressParserResult.remainingTokens
        ).thenReturn(remainingTokens)
        val dataParserResult = Mockito.mock(ParserResult::class.java)
        Mockito.`when`(
            parserResultBuilder.buildParserResult(
                closeTokenResult,
                remainingTokens
            )
        ).thenReturn(dataParserResult)
        val dataResultingNode = Mockito.mock(Node::class.java)
        Mockito.`when`(dataParserResult.resultingNode).thenReturn(dataResultingNode)
        val result = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateExpressionNode(
                addressResultingNode,
                dataResultingNode
            )
        ).thenReturn(result)
        val dataRemainingTokens: List<Token> = LinkedList()
        Mockito.`when`(dataParserResult.remainingTokens).thenReturn(dataRemainingTokens)
        val expected = Mockito.mock(ParserResult::class.java)
        Mockito.`when`(
            parserResultBuilder.buildParserResult(
                result,
                dataRemainingTokens
            )
        ).thenReturn(expected)
        val actual = expressionNodeParser.parseExpressionNode(tokens)
        Assertions.assertEquals(expected, actual)
    }
}