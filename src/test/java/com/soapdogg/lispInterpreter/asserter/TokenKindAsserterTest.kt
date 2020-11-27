package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.datamodels.Token
import com.soapdogg.lispInterpreter.datamodels.TokenKind
import com.soapdogg.lispInterpreter.exceptions.UnexpectedTokenKindException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class TokenKindAsserterTest {
    private val startingTokenKind = TokenKind.OPEN_TOKEN
    private val startingTokenKindSet = setOf(startingTokenKind)
    private val token = Mockito.mock(Token::class.java)
    private val tokenKindAsserter = TokenKindAsserter(startingTokenKindSet)
    @Test
    fun noExceptionIsNotNullTest() {
        Assertions.assertDoesNotThrow<Token> { tokenKindAsserter.assertTokenIsNotNull(Optional.of(token)) }
    }

    @Test
    fun unexpectedTokenKindIsNotNullTest() {
        Assertions.assertThrows(
            UnexpectedTokenKindException::class.java
        ) { tokenKindAsserter.assertTokenIsNotNull(Optional.empty()) }
    }

    @Test
    fun noExceptionAtomOrOpenTest() {
        Mockito.`when`(token.tokenKind).thenReturn(startingTokenKind)
        Assertions.assertDoesNotThrow { tokenKindAsserter.assertTokenIsAtomOrOpen(token) }
    }

    @Test
    fun unexpectedTokenKindAtomOrOpenExceptionTest() {
        Mockito.`when`(token.tokenKind).thenReturn(TokenKind.CLOSE_TOKEN)
        Assertions.assertThrows(
            UnexpectedTokenKindException::class.java
        ) { tokenKindAsserter.assertTokenIsAtomOrOpen(token) }
    }

    @Test
    fun noExceptionCloseTest() {
        Mockito.`when`(token.tokenKind).thenReturn(TokenKind.CLOSE_TOKEN)
        Assertions.assertDoesNotThrow { tokenKindAsserter.assertTokenIsClose(token) }
    }

    @Test
    fun unexpectedTokenKindCloseExceptionTest() {
        Mockito.`when`(token.tokenKind).thenReturn(TokenKind.OPEN_TOKEN)
        Assertions.assertThrows(
            UnexpectedTokenKindException::class.java
        ) { tokenKindAsserter.assertTokenIsClose(token) }
    }
}