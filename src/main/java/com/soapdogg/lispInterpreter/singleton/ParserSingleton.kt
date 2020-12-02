package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.parser.*

enum class ParserSingleton {
    INSTANCE;

    val atomNodeParser: AtomNodeParser
    val parserResultBuilder: ParserResultBuilder = ParserResultBuilder()
    val expressionNodeParser: ExpressionNodeParser
    val nodeParser: NodeParser
    val rootParser: RootParser

    init {
        atomNodeParser = AtomNodeParser(
            GeneratorSingleton.INSTANCE.nodeGenerator,
            ConverterSingleton.INSTANCE.nodeConverter,
        )

        expressionNodeParser = ExpressionNodeParser(
            GeneratorSingleton.INSTANCE.nodeGenerator,
            atomNodeParser,
            parserResultBuilder
        )
        nodeParser = NodeParser(
            expressionNodeParser,
            parserResultBuilder,
            atomNodeParser
        )
        rootParser = RootParser(
            nodeParser
        )
    }
}