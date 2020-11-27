package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.datamodels.Token
import com.soapdogg.lispInterpreter.datamodels.TokenKind
import com.soapdogg.lispInterpreter.exceptions.UnexpectedTokenKindException
import java.util.*

class TokenKindAsserter(
    private val startingTokenKindSet: Set<TokenKind>
) {

    fun assertTokenIsNotNull(
        optionalToken: Optional<Token>
    ): Token {
        if (optionalToken.isEmpty) {
            val errorMessage = "Expected a token.\nActual: null\n"
            throw UnexpectedTokenKindException(errorMessage)
        }
        return optionalToken.get()
    }

    fun assertTokenIsAtomOrOpen(
        token: Token
    ) {
        val result = startingTokenKindSet.contains(token.tokenKind)
        if (result) return
        val errorMessage = """Expected either an ATOM or OPEN token.${'\n'}Actual: ${token.tokenKind}    Value: ${token.value}${'\n'}"""
        throw UnexpectedTokenKindException(errorMessage)
    }

    fun assertTokenIsClose(
        token: Token
    ) {
        val result = token.tokenKind === TokenKind.CLOSE_TOKEN
        if (result) return
        val errorMessage = """Expected CLOSED token.${'\n'}Actual: ${token.tokenKind}    Value: ${token.value}${'\n'}"""
        throw UnexpectedTokenKindException(errorMessage)
    }
}