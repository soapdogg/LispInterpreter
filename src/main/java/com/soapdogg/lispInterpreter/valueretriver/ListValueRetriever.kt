package com.soapdogg.lispInterpreter.valueretriver

import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.exceptions.NotAListException
import com.soapdogg.lispInterpreter.printer.DotNotationPrinter

class ListValueRetriever(
    private val dotNotationPrinter: DotNotationPrinter
){
    fun retrieveListValue(
        node: Node,
        functionName: String,
        variableNameToValueMap: Map<String, Node>
    ): ExpressionNode {
        if (node is ExpressionNode) {
            return node
        }
        val nodeValue = (node as AtomNode).value
        val isVariable = variableNameToValueMap.containsKey(nodeValue)
        if (isVariable) {
            val result = variableNameToValueMap.getValue(nodeValue)
            if (result is ExpressionNode) return result
        }
        val sb = """Error! Parameter of $functionName is not a list.    Actual: ${dotNotationPrinter.printInDotNotation(node)}${'\n'}"""
        throw NotAListException(sb)
    }

    fun retrieveListValue(
        node: NodeV2,
        functionName: String,
        variableNameToValueMap: Map<String, NodeV2>
    ): ExpressionListNode {
        if (node is ExpressionListNode) {
            return node
        }
        val nodeValue = (node as AtomNode).value
        val isVariable = variableNameToValueMap.containsKey(nodeValue)
        if (isVariable) {
            val result = variableNameToValueMap.getValue(nodeValue)
            if (result is ExpressionListNode) return result
        }
        val sb = """Error! Parameter of $functionName is not a list.    Actual: ${dotNotationPrinter.printInDotNotation(node)}${'\n'}"""
        throw NotAListException(sb)
    }
}