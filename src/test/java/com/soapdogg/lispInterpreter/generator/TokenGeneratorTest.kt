package com.soapdogg.lispInterpreter.generator

import com.soapdogg.lispInterpreter.constants.TokenValueConstants
import com.soapdogg.lispInterpreter.datamodels.TokenKind
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TokenGeneratorTest {
    private val value = "value"
    private val tokenGenerator = TokenGenerator()

    @Test
    fun generateCloseTokenTest() {
        val (tokenKind, value1) = tokenGenerator.generateCloseToken()
        Assertions.assertEquals(TokenKind.CLOSE_TOKEN, tokenKind)
        Assertions.assertEquals(TokenValueConstants.CLOSE_PARENTHESES.toString(), value1)
    }

    @Test
    fun generateOpenTokenTest() {
        val (tokenKind, value1) = tokenGenerator.generateOpenToken()
        Assertions.assertEquals(TokenKind.OPEN_TOKEN, tokenKind)
        Assertions.assertEquals(TokenValueConstants.OPEN_PARENTHESES.toString(), value1)
    }

    @Test
    fun generateNumericTokenTest() {
        val (tokenKind, value1) = tokenGenerator.generateNumericToken(value)
        Assertions.assertEquals(TokenKind.NUMERIC_TOKEN, tokenKind)
        Assertions.assertEquals(value, value1)
    }

    @Test
    fun generateLiteralTokenTest() {
        val (tokenKind, value1) = tokenGenerator.generateLiteralToken(value)
        Assertions.assertEquals(TokenKind.LITERAL_TOKEN, tokenKind)
        Assertions.assertEquals(value, value1)
    }
}