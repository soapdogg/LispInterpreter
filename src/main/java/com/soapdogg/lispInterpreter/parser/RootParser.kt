package com.soapdogg.lispInterpreter.parser

import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.Token
import java.util.*

class RootParser (
  private val nodeParser: NodeParser
) {

    fun parse(tokens: List<Token>): List<Node> {
        var remaining = tokens
        val rootNodes: MutableList<Node> = ArrayList()
        while (remaining.isNotEmpty()) {
            val parserResult = nodeParser.parseIntoNode(remaining)
            rootNodes.add(parserResult.resultingNode)
            remaining = parserResult.remainingTokens
        }
        return rootNodes
    }
}