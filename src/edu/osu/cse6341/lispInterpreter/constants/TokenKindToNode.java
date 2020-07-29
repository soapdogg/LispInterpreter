package edu.osu.cse6341.lispInterpreter.constants;

import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

import java.util.Set;

public class TokenKindToNode {

    public static final Set<TokenKind> startingTokenKindSet = Set.of(
        TokenKind.OPEN_TOKEN,
        TokenKind.NUMERIC_TOKEN,
        TokenKind.LITERAL_TOKEN
    );
}
