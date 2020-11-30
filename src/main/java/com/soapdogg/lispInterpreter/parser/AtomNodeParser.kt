package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.ParserResult
import com.soapdogg.lispInterpreter.datamodels.Token
import com.soapdogg.lispInterpreter.generator.NodeGenerator

class AtomNodeParser(
    private val nodeGenerator: NodeGenerator,
    private val parserResultBuilder: ParserResultBuilder
) {
    fun parseAtomNode(
        tokens: List<Token>
    ): ParserResult {
        val value = tokens[0].value
        val atomNode: Node = nodeGenerator.generateAtomNode(value)
        return parserResultBuilder.buildParserResult(
            atomNode,
            tokens.subList(1, tokens.size)
        )
    }
}