package com.soapdogg.lispInterpreter.singleton;

import com.soapdogg.lispInterpreter.parser.AtomNodeParser;
import com.soapdogg.lispInterpreter.parser.ExpressionNodeParser;
import com.soapdogg.lispInterpreter.parser.NodeParser;
import com.soapdogg.lispInterpreter.parser.RootParser;
import lombok.Getter;

@Getter
public enum ParserSingleton {
    INSTANCE;

    private final AtomNodeParser atomNodeParser;
    private final ExpressionNodeParser expressionNodeParser;
    private final NodeParser nodeParser;
    private final RootParser rootParser;

    ParserSingleton() {
        atomNodeParser = AtomNodeParser.newInstance(GeneratorSingleton.INSTANCE.getNodeGenerator());
        expressionNodeParser = ExpressionNodeParser.newInstance(
            AsserterSingleton.INSTANCE.getTokenKindAsserter(),
            GeneratorSingleton.INSTANCE.getNodeGenerator(),
            atomNodeParser
        );
        nodeParser = NodeParser.newInstance(
            AsserterSingleton.INSTANCE.getTokenKindAsserter(),
            expressionNodeParser,
            atomNodeParser
        );
        rootParser = RootParser.newInstance(
            nodeParser
        );
    }
}
