package com.soapdogg.lispInterpreter.generator

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ParserResult

class ParserResultGenerator(
    private val nodeGenerator: NodeGenerator
) {

    fun generateParserResultForNilAtomNode(
        startingPoint: Int
    ): ParserResult {
        val nilAtomNode = nodeGenerator.generateAtomNode(ReservedValuesConstants.NIL)
        return ParserResult(
            nilAtomNode,
            startingPoint + 2
        )
    }

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