package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.interpreter.Interpreter;
import edu.osu.cse6341.lispInterpreter.interpreter.RootNodePartitioner;
import lombok.Getter;

@Getter
public enum InterpreterSingleton {
    INSTANCE;

    private final RootNodePartitioner rootNodePartitioner;
    private final Interpreter interpreter;

    InterpreterSingleton() {
        rootNodePartitioner = RootNodePartitioner.newInstance();
        interpreter = Interpreter.newInstance(
            TokenizerSingleton.INSTANCE.getTokenizer(),
            ParserSingleton.INSTANCE.getParser(),
            ProgramSingleton.INSTANCE.getProgram(),
            rootNodePartitioner,
            GeneratorSingleton.INSTANCE.getUserDefinedFunctionGenerator(),
            PrinterSingleton.INSTANCE.getListNotationPrinter()
        );
    }
}
