package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.parser.*

enum class ParserSingleton {
    INSTANCE;

    val atomNodeParser: AtomNodeParser
    val expressionListNodeParser: ExpressionListNodeParser
    val nodeParser: NodeParser
    val rootParser: RootParser

    init {
        atomNodeParser = AtomNodeParser(
            GeneratorSingleton.INSTANCE.nodeGenerator,
            ConverterSingleton.INSTANCE.nodeConverter,
        )
        expressionListNodeParser = ExpressionListNodeParser(
            GeneratorSingleton.INSTANCE.nodeGenerator
        )
        nodeParser = NodeParser(
            expressionListNodeParser,
            ConverterSingleton.INSTANCE.nodeConverter,
            atomNodeParser
        )
        rootParser = RootParser(
            nodeParser
        )
    }
}