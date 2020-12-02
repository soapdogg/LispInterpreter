package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.parser.*

enum class ParserSingleton {
    INSTANCE;

    val expressionListNodeParser: ExpressionListNodeParser
    val nodeParser: NodeParser
    val rootParser: RootParser

    init {
        expressionListNodeParser = ExpressionListNodeParser(
            GeneratorSingleton.INSTANCE.nodeGenerator,
            GeneratorSingleton.INSTANCE.parserResultGenerator
        )
        nodeParser = NodeParser(
            expressionListNodeParser,
            ConverterSingleton.INSTANCE.nodeConverter
        )
        rootParser = RootParser(
            nodeParser
        )
    }
}