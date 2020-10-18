package com.soapdogg.lispInterpreter.asserter;

import com.soapdogg.lispInterpreter.datamodels.Token;
import com.soapdogg.lispInterpreter.datamodels.TokenKind;
import com.soapdogg.lispInterpreter.exceptions.UnexpectedTokenKindException;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.Set;

@AllArgsConstructor(staticName = "newInstance")
public class TokenKindAsserter {

    private final Set<TokenKind> startingTokenKindSet;

    public Token assertTokenIsNotNull(
        final Optional<Token> optionalToken
    ) throws UnexpectedTokenKindException {
        if (optionalToken.isEmpty()) {
            String errorMessage = "Expected a token.\nActual: null\n";
            throw new UnexpectedTokenKindException(errorMessage);
        }
        return optionalToken.get();
    }

    public void assertTokenIsAtomOrOpen(
        final Token token
    ) throws Exception{
        boolean result = startingTokenKindSet.contains(token.getTokenKind());
        if (result) return;
        String errorMessage = "Expected either an ATOM or OPEN token.\nActual: " + token.getTokenKind().toString() +
                "    Value: " +
                token.getValue() +
                '\n';
        throw new UnexpectedTokenKindException(errorMessage);
    }

    public void assertTokenIsClose(
        final Token token
    ) throws Exception {
        boolean result = token.getTokenKind() == TokenKind.CLOSE_TOKEN;
        if (result) return;
        String errorMessage = "Expected CLOSED token.\nActual: " + token.getTokenKind().toString() +
                "    Value: " +
                token.getValue() +
                '\n';
        throw new UnexpectedTokenKindException(errorMessage);
    }
}
