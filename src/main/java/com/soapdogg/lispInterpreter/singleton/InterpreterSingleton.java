package com.soapdogg.lispInterpreter.singleton;

import com.soapdogg.lispInterpreter.interpreter.Interpreter;
import com.soapdogg.lispInterpreter.interpreter.RootNodePartitioner;
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
            ParserSingleton.INSTANCE.getRootParser(),
            EvaluatorSingleton.INSTANCE.getProgramEvaluator(),
            rootNodePartitioner,
            GeneratorSingleton.INSTANCE.getUserDefinedFunctionGenerator(),
            PrinterSingleton.INSTANCE.getListNotationPrinter()
        );
    }
}
