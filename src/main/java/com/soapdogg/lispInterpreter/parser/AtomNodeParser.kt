package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.ParserResult
import com.soapdogg.lispInterpreter.datamodels.Token
import com.soapdogg.lispInterpreter.generator.NodeGenerator

class AtomNodeParser(
    private val nodeGenerator: NodeGenerator,
    private val nodeConverter: NodeConverter,
    private val parserResultBuilder: ParserResultBuilder
) {
    fun parseAtomNode(
        tokens: List<Token>
    ): ParserResult {
        val value = tokens[0].value
        val atomNode = nodeGenerator.generateAtomNode(value)
        val convertedAtomNode = nodeConverter.convertNodeV2ToNode(atomNode)
        return parserResultBuilder.buildParserResult(
            convertedAtomNode,
            tokens.subList(1, tokens.size)
        )
    }
}