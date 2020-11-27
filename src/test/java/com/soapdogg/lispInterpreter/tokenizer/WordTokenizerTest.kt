package com.soapdogg.lispInterpreter.tokenizer

import com.soapdogg.lispInterpreter.datamodels.Token
import com.soapdogg.lispInterpreter.determiner.LiteralTokenValueEndIndexDeterminer
import com.soapdogg.lispInterpreter.determiner.NumericTokenValueEndIndexDeterminer
import com.soapdogg.lispInterpreter.generator.TokenGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class WordTokenizerTest {
    private val tokenGenerator: TokenGenerator = Mockito.mock(TokenGenerator::class.java)
    private val numericTokenValueEndIndexDeterminer: NumericTokenValueEndIndexDeterminer = Mockito.mock(NumericTokenValueEndIndexDeterminer::class.java)
    private val literalTokenValueEndIndexDeterminer: LiteralTokenValueEndIndexDeterminer = Mockito.mock(LiteralTokenValueEndIndexDeterminer::class.java)
    private val wordTokenizer: WordTokenizer = WordTokenizer(
        tokenGenerator,
        numericTokenValueEndIndexDeterminer,
        literalTokenValueEndIndexDeterminer
    )

    @Test
    fun closeParenthesesTest() {
        val word = ")"
        val closeToken = Mockito.mock(Token::class.java)
        Mockito.`when`(closeToken.value).thenReturn(word)
        Mockito.`when`(tokenGenerator.generateCloseToken()).thenReturn(closeToken)
        val actual = wordTokenizer.tokenizeWord(word)
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(closeToken, actual[0])
        Mockito.verifyNoInteractions(numericTokenValueEndIndexDeterminer)
        Mockito.verifyNoInteractions(literalTokenValueEndIndexDeterminer)
    }

    @Test
    fun openParenthesesTest() {
        val word = "("
        val openToken = Mockito.mock(Token::class.java)
        Mockito.`when`(openToken.value).thenReturn(word)
        Mockito.`when`(tokenGenerator.generateOpenToken()).thenReturn(openToken)
        val actual = wordTokenizer.tokenizeWord(word)
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(openToken, actual[0])
        Mockito.verifyNoInteractions(numericTokenValueEndIndexDeterminer)
        Mockito.verifyNoInteractions(literalTokenValueEndIndexDeterminer)
    }

    @Test
    fun numericTokenValueTest() {
        val word = "123"
        val pos = word.length
        Mockito.`when`(
            numericTokenValueEndIndexDeterminer.determineEndIndexOfNumericTokenValue(
                word,
                0
            )
        ).thenReturn(pos)
        val numericToken = Mockito.mock(Token::class.java)
        Mockito.`when`(numericToken.value).thenReturn(word)
        Mockito.`when`(tokenGenerator.generateNumericToken(word)).thenReturn(numericToken)
        val actual = wordTokenizer.tokenizeWord(word)
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(numericToken, actual[0])
        Mockito.verifyNoInteractions(literalTokenValueEndIndexDeterminer)
    }

    @Test
    fun literalTokenValueTest() {
        val word = "ABC"
        val pos = word.length
        Mockito.`when`(
            literalTokenValueEndIndexDeterminer.determineEndIndexOfLiteralTokenValue(
                word,
                0
            )
        ).thenReturn(pos)
        val literalToken = Mockito.mock(Token::class.java)
        Mockito.`when`(literalToken.value).thenReturn(word)
        Mockito.`when`(tokenGenerator.generateLiteralToken(word)).thenReturn(literalToken)
        val actual = wordTokenizer.tokenizeWord(word)
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(literalToken, actual[0])
        Mockito.verifyNoInteractions(numericTokenValueEndIndexDeterminer)
    }
}