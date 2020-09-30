package com.soapdogg.lispInterpreter.singleton;

import com.soapdogg.lispInterpreter.parser.Parser;
import lombok.Getter;

@Getter
public enum ParserSingleton {
    INSTANCE;

    private final Parser parser;

    ParserSingleton() {
        parser = Parser.newInstance(
            AsserterSingleton.INSTANCE.getTokenKindAsserter(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
    }
}
