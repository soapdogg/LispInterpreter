package com.soapdogg.lispInterpreter.generator;

import com.soapdogg.lispInterpreter.datamodels.Token;
import com.soapdogg.lispInterpreter.datamodels.TokenKind;
import com.soapdogg.lispInterpreter.constants.TokenValueConstants;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class TokenGenerator {

    public Token generateCloseToken() {
        return new Token(
            TokenKind.CLOSE_TOKEN,
            String.valueOf(TokenValueConstants.CLOSE_PARENTHESES)
        );
    }

    public Token generateOpenToken() {
        return new Token(
            TokenKind.OPEN_TOKEN,
            String.valueOf(TokenValueConstants.OPEN_PARENTHESES)
        );
    }

    public Token generateNumericToken(
        String value
    ) {
        return new Token(
            TokenKind.NUMERIC_TOKEN,
            value
        );
    }

    public Token generateLiteralToken(
        String value
    ) {
        return new Token(
            TokenKind.LITERAL_TOKEN,
            value
        );
    }
}
