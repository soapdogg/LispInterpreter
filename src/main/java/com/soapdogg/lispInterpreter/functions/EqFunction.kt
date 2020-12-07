package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter

class EqFunction(
    private val nodeEvaluator: NodeEvaluator,
    private val listNotationPrinter: ListNotationPrinter,
    private val nodeGenerator: NodeGenerator,
): LispFunctionV2 {

    override fun evaluateLispFunction(
        params: ExpressionListNode,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, NodeV2>
    ): NodeV2 {
        val evaluatedAddress = nodeEvaluator.evaluateV2(
            params.children[1],
            userDefinedFunctions,
            variableNameToValueMap
        )
        val evaluatedData = nodeEvaluator.evaluateV2(
            params.children[2],
            userDefinedFunctions,
            variableNameToValueMap
        )
        val leftValue = listNotationPrinter.printInListNotation(evaluatedAddress)
        val rightValue = listNotationPrinter.printInListNotation(evaluatedData)

        val result = leftValue == rightValue
        return nodeGenerator.generateAtomNode(result)
    }
}