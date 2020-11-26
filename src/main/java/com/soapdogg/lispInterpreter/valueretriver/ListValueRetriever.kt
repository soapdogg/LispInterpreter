package com.soapdogg.lispInterpreter.valueretriver

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer
import com.soapdogg.lispInterpreter.exceptions.NotAListException
import com.soapdogg.lispInterpreter.printer.DotNotationPrinter

class ListValueRetriever(
    private val expressionNodeDeterminer: ExpressionNodeDeterminer,
    private val dotNotationPrinter: DotNotationPrinter
){
    fun retrieveListValue(
        node: Node,
        functionName: String,
        variableNameToValueMap: Map<String, Node>
    ): ExpressionNode {
        val isNodeAList = expressionNodeDeterminer.isExpressionNode(node)
        if (isNodeAList) {
            return node as ExpressionNode
        }
        val nodeValue = (node as AtomNode).value
        val isVariable = variableNameToValueMap.containsKey(nodeValue)
        if (isVariable) {
            val result = variableNameToValueMap.getValue(nodeValue)
            val isVariableList = expressionNodeDeterminer.isExpressionNode(result)
            if (isVariableList) return result as ExpressionNode
        }
        val sb = """Error! Parameter of $functionName is not a list.    Actual: ${dotNotationPrinter.printInDotNotation(node)}${'\n'}"""
        throw NotAListException(sb)
    }
}