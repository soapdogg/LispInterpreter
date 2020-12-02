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
            AsserterSingleton.INSTANCE.tokenKindAsserter,
            parserResultBuilder
        )
        expressionNodeParser = ExpressionNodeParser(
            AsserterSingleton.INSTANCE.tokenKindAsserter,
            GeneratorSingleton.INSTANCE.nodeGenerator,
            expressionNodeFinisher,
            atomNodeParser,
            parserResultBuilder
        )
        nodeParser = NodeParser(
            AsserterSingleton.INSTANCE.tokenKindAsserter,
            expressionNodeParser,
            expressionNodeFinisher,
            atomNodeParser
        )
        rootParser = RootParser(
            nodeParser
        )
    }
}