package com.soapdogg.lispInterpreter.singleton;

import com.soapdogg.lispInterpreter.parser.AtomNodeParser;
import com.soapdogg.lispInterpreter.parser.ExpressionNodeFinisher;
import com.soapdogg.lispInterpreter.parser.ExpressionNodeParser;
import com.soapdogg.lispInterpreter.parser.NodeParser;
import com.soapdogg.lispInterpreter.parser.ParserResultBuilder;
import com.soapdogg.lispInterpreter.parser.RootParser;
import lombok.Getter;

@Getter
public enum ParserSingleton {
    INSTANCE;

    private final AtomNodeParser atomNodeParser;
    private final ParserResultBuilder parserResultBuilder;
    private final ExpressionNodeParser expressionNodeParser;
    private final ExpressionNodeFinisher expressionNodeFinisher;
    private final NodeParser nodeParser;
    private final RootParser rootParser;

    ParserSingleton() {
        parserResultBuilder = new ParserResultBuilder();
        atomNodeParser = new AtomNodeParser(
            GeneratorSingleton.INSTANCE.getNodeGenerator(),
            parserResultBuilder
        );
        expressionNodeFinisher = new ExpressionNodeFinisher(
            AsserterSingleton.INSTANCE.getTokenKindAsserter(),
            parserResultBuilder
        );
        expressionNodeParser = new ExpressionNodeParser(
            AsserterSingleton.INSTANCE.getTokenKindAsserter(),
            GeneratorSingleton.INSTANCE.getNodeGenerator(),
            expressionNodeFinisher,
            atomNodeParser,
            parserResultBuilder
        );

        nodeParser = new NodeParser(
            AsserterSingleton.INSTANCE.getTokenKindAsserter(),
            expressionNodeParser,
            expressionNodeFinisher,
            atomNodeParser
        );
        rootParser = new RootParser(
            nodeParser
        );
    }
}
