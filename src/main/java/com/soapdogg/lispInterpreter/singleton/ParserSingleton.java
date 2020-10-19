package com.soapdogg.lispInterpreter.singleton;

import com.soapdogg.lispInterpreter.parser.AtomNodeParser;
import com.soapdogg.lispInterpreter.parser.NodeParser;
import com.soapdogg.lispInterpreter.parser.RootParser;
import lombok.Getter;

@Getter
public enum ParserSingleton {
    INSTANCE;

    private final AtomNodeParser atomNodeParser;
    private final NodeParser nodeParser;
    private final RootParser rootParser;

    ParserSingleton() {
        atomNodeParser = AtomNodeParser.newInstance(GeneratorSingleton.INSTANCE.getNodeGenerator());
        nodeParser = NodeParser.newInstance(
            AsserterSingleton.INSTANCE.getTokenKindAsserter(),
            GeneratorSingleton.INSTANCE.getNodeGenerator(),
            atomNodeParser
        );
        rootParser = RootParser.newInstance(
            nodeParser
        );
    }
}
