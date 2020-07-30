package edu.osu.cse6341.lispInterpreter.asserter;

import edu.osu.cse6341.lispInterpreter.exceptions.UnexpectedTokenKindException;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Set;

public class TokenKindAsserterTest {

    private TokenKind startingTokenKind;

    private IToken token;

    private TokenKindAsserter tokenKindAsserter;

    @BeforeEach
    void setup() {
        startingTokenKind = TokenKind.OPEN_TOKEN;
        Set<TokenKind> startingTokenKindSet = Set.of(startingTokenKind);

        token = Mockito.mock(IToken.class);

        tokenKindAsserter = TokenKindAsserter.newInstance(startingTokenKindSet);
    }

    @Test
    void noExceptionTest() {
        Mockito.when(token.getTokenKind()).thenReturn(startingTokenKind);

        Assertions.assertDoesNotThrow(
            () -> tokenKindAsserter.assertTokenIsAtomOrOpen(token)
        );
    }

    @Test
    void unexpectedTokenKindExceptionTest() {
        Mockito.when(token.getTokenKind()).thenReturn(TokenKind.CLOSE_TOKEN);

        Assertions.assertThrows(
            UnexpectedTokenKindException.class,
            () -> tokenKindAsserter.assertTokenIsAtomOrOpen(token)
        );
    }
}
