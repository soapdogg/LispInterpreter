package edu.osu.cse6341.lispInterpreter.asserter;

import edu.osu.cse6341.lispInterpreter.exceptions.UnexpectedTokenKindException;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor(staticName = "newInstance")
public class TokenKindAsserter {

    private final Set<TokenKind> startingTokenKindSet;

    public void assertTokenIsAtomOrOpen(
        final IToken token
    ) throws Exception{
        boolean result = startingTokenKindSet.contains(token.getTokenKind());
        if (result) return;
        String errorMessage = "Expected either an ATOM or OPEN token.\nActual: " + token.getTokenKind().toString() +
            "    Value: " +
            token.toString() +
            '\n';
        throw new UnexpectedTokenKindException(errorMessage);
    }
}
