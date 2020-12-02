package com.soapdogg.lispInterpreter.generator

import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ParserResult

class ParserResultGenerator(
    private val nodeGenerator: NodeGenerator
) {

    fun generateParserResultForAtomNode(
        value: String
    ): ParserResult {
        val resultingNode = nodeGenerator.generateAtomNode(value)
        return ParserResult(
            resultingNode,
            1
        )
    }

    fun generateParserResultForExpressionListNode(
        children: List<NodeV2>,
        nextIndex: Int
    ): ParserResult {
        val resultingNode = nodeGenerator.generateExpressionListNode(children)
        return ParserResult(
            resultingNode,
            nextIndex
        )
    }
}