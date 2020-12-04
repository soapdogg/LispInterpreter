package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.interpreter.Interpreter
import com.soapdogg.lispInterpreter.interpreter.RootNodePartitioner

enum class InterpreterSingleton {
    INSTANCE;

    val rootNodePartitioner: RootNodePartitioner = RootNodePartitioner()
    val interpreter: Interpreter

    init {
        interpreter = Interpreter(
            TokenizerSingleton.INSTANCE.tokenizer,
            ParserSingleton.INSTANCE.rootParser,
            EvaluatorSingleton.INSTANCE.programEvaluator,
            rootNodePartitioner,
            GeneratorSingleton.INSTANCE.defunFunction,
            ConverterSingleton.INSTANCE.nodeConverter,
            PrinterSingleton.INSTANCE.listNotationPrinter
        )
    }
}