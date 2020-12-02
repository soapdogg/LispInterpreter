package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.Token
import com.soapdogg.lispInterpreter.generator.NodeGenerator

class AtomNodeParser(
    private val nodeGenerator: NodeGenerator,
    private val nodeConverter: NodeConverter
) {
    fun parseAtomNode(
        token: Token
    ): Node {
        val value = token.value
        val atomNode = nodeGenerator.generateAtomNode(value)
        return nodeConverter.convertNodeV2ToNode(atomNode)
    }
}