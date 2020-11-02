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
        parserResultBuilder = ParserResultBuilder.newInstance();
        atomNodeParser = AtomNodeParser.newInstance(
            GeneratorSingleton.INSTANCE.getNodeGenerator(),
            parserResultBuilder
        );
        expressionNodeFinisher = ExpressionNodeFinisher.newInstance(
            AsserterSingleton.INSTANCE.getTokenKindAsserter(),
            parserResultBuilder
        );
        expressionNodeParser = ExpressionNodeParser.newInstance(
            AsserterSingleton.INSTANCE.getTokenKindAsserter(),
            GeneratorSingleton.INSTANCE.getNodeGenerator(),
            expressionNodeFinisher,
            atomNodeParser,
            parserResultBuilder
        );

        nodeParser = NodeParser.newInstance(
            AsserterSingleton.INSTANCE.getTokenKindAsserter(),
            expressionNodeParser,
            expressionNodeFinisher,
            atomNodeParser
        );
        rootParser = RootParser.newInstance(
            nodeParser
        );
    }
}
