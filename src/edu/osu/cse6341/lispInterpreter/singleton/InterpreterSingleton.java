package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.Interpreter;
import lombok.Getter;

@Getter
public enum InterpreterSingleton {
    INSTANCE;

    private final Interpreter interpreter;

    InterpreterSingleton() {
        interpreter = Interpreter.newInstance(
            TokenizerSingleton.INSTANCE.getTokenizer(),
            ParserSingleton.INSTANCE.getParser(),
            PrinterSingleton.INSTANCE.getDotNotationPrinter()
        );
    }
}
