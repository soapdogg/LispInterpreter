package com.soapdogg.lispInterpreter.datamodels;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Queue;

@Getter
@AllArgsConstructor(staticName = "newInstance")
public class ParserResult {

    private final Node resultingNode;
    private final Queue<Token> remainingTokens;
}