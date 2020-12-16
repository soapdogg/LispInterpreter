package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever
import java.util.*

class GreaterFunction(
    private val numericValueRetriever: NumericValueRetriever,
    private val nodeGenerator: NodeGenerator
) : Function {

    override fun evaluate(
        params: Stack<NodeV2>,
        variableMap: Map<String, NodeV2>
    ): NodeV2 {
        val first = params.pop()
        val second = params.pop()
        val firstNumeric = numericValueRetriever.retrieveNumericValue(
            first,
            FunctionNameConstants.GREATER,
            1,
            variableMap
        )
        val secondNumeric = numericValueRetriever.retrieveNumericValue(
            second,
            FunctionNameConstants.GREATER,
            2,
            variableMap
        )

        val result = firstNumeric > secondNumeric

        return nodeGenerator.generateAtomNode(result)
    }
}