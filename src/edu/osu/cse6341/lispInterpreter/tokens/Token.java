package edu.osu.cse6341.lispInterpreter.tokens;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "newInstance")
public class Token {
    private final TokenKind tokenKind;
    private final String value;
}
