package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.parser.Parser;
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
