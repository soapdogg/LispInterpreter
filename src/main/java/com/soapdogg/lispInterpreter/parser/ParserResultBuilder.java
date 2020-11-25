package com.soapdogg.lispInterpreter.parser;

import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.ParserResult;
import com.soapdogg.lispInterpreter.datamodels.Token;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "newInstance")
public class ParserResultBuilder {

    public ParserResult buildParserResult(
        final Node resultingNode,
        final List<Token> remainingTokens
    ) {
        return ParserResult.newInstance(
            resultingNode,
            remainingTokens
        );
    }
}
