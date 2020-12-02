package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.parser.*

enum class ParserSingleton {
    INSTANCE;

    val atomNodeParser: AtomNodeParser
    val parserResultBuilder: ParserResultBuilder = ParserResultBuilder()
    val expressionNodeParser: ExpressionNodeParser
    val expressionNodeFinisher: ExpressionNodeFinisher
    val nodeParser: NodeParser
    val rootParser: RootParser

    init {
        atomNodeParser = AtomNodeParser(
            GeneratorSingleton.INSTANCE.nodeGenerator,
            ConverterSingleton.INSTANCE.nodeConverter,
            parserResultBuilder
        )
        expressionNodeFinisher = ExpressionNodeFinisher(
            parserResultBuilder
        )
        expressionNodeParser = ExpressionNodeParser(
            GeneratorSingleton.INSTANCE.nodeGenerator,
            expressionNodeFinisher,
            atomNodeParser,
            parserResultBuilder
        )
        nodeParser = NodeParser(
            expressionNodeParser,
            expressionNodeFinisher,
            atomNodeParser
        )
        rootParser = RootParser(
            nodeParser
        )
    }
}