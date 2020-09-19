package edu.osu.cse6341.lispInterpreter.asserter;

import edu.osu.cse6341.lispInterpreter.exceptions.UnexpectedTokenKindException;
import edu.osu.cse6341.lispInterpreter.datamodels.Token;
import edu.osu.cse6341.lispInterpreter.datamodels.TokenKind;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor(staticName = "newInstance")
public class TokenKindAsserter {

    private final Set<TokenKind> startingTokenKindSet;

    public void assertTokenIsNotNull(
        final Token token
    ) throws UnexpectedTokenKindException {
        if (token == null) {
            String errorMessage = "Expected a token.\nActual: null\n";
            throw new UnexpectedTokenKindException(errorMessage);
        }
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
