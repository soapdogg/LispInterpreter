package edu.osu.cse6341.lispInterpreter.asserter;

import edu.osu.cse6341.lispInterpreter.exceptions.UnexpectedTokenKindException;
import edu.osu.cse6341.lispInterpreter.tokens.Token;
import edu.osu.cse6341.lispInterpreter.tokens.TokenKind;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Set;

public class TokenKindAsserterTest {

    private TokenKind startingTokenKind;

    private Token token;

    private TokenKindAsserter tokenKindAsserter;

    @BeforeEach
    void setup() {
        startingTokenKind = TokenKind.OPEN_TOKEN;
        Set<TokenKind> startingTokenKindSet = Set.of(startingTokenKind);

        token = Mockito.mock(Token.class);

        tokenKindAsserter = TokenKindAsserter.newInstance(startingTokenKindSet);
    }

    @Test
    void noExceptionIsNotNullTest() {
        Assertions.assertDoesNotThrow(
            () -> tokenKindAsserter.assertTokenIsNotNull(token)
        );
    }

    @Test
    void unexpectedTokenKindIsNotNullTest() {
        Assertions.assertThrows(
            UnexpectedTokenKindException.class,
            () -> tokenKindAsserter.assertTokenIsNotNull(null)
        );
    }

    @Test
    void noExceptionAtomOrOpenTest() {
        Mockito.when(token.getTokenKind()).thenReturn(startingTokenKind);

        Assertions.assertDoesNotThrow(
            () -> tokenKindAsserter.assertTokenIsAtomOrOpen(token)
        );
    }

    @Test
    void unexpectedTokenKindAtomOrOpenExceptionTest() {
        Mockito.when(token.getTokenKind()).thenReturn(TokenKind.CLOSE_TOKEN);

        Assertions.assertThrows(
            UnexpectedTokenKindException.class,
            () -> tokenKindAsserter.assertTokenIsAtomOrOpen(token)
        );
    }

    @Test
    void noExceptionCloseTest() {
        Mockito.when(token.getTokenKind()).thenReturn(TokenKind.CLOSE_TOKEN);

        Assertions.assertDoesNotThrow(
            () -> tokenKindAsserter.assertTokenIsClose(token)
        );
    }

    @Test
    void unexpectedTokenKindCloseExceptionTest() {
        Mockito.when(token.getTokenKind()).thenReturn(TokenKind.OPEN_TOKEN);

        Assertions.assertThrows(
            UnexpectedTokenKindException.class,
            () -> tokenKindAsserter.assertTokenIsClose(token)
        );
    }
}
