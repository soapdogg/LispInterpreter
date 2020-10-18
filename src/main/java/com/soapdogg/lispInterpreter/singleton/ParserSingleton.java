package com.soapdogg.lispInterpreter.singleton;

import com.soapdogg.lispInterpreter.parser.NodeParser;
import com.soapdogg.lispInterpreter.parser.RootParser;
import lombok.Getter;

@Getter
public enum ParserSingleton {
    INSTANCE;

    private final NodeParser nodeParser;
    private final RootParser rootParser;

    ParserSingleton() {
        nodeParser = NodeParser.newInstance(
            AsserterSingleton.INSTANCE.getTokenKindAsserter(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
        rootParser = RootParser.newInstance(
            nodeParser
        );
    }
}
