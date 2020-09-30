package com.soapdogg.lispInterpreter.datamodels;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "newInstance")
public class Token {
    private final TokenKind tokenKind;
    private final String value;
}
