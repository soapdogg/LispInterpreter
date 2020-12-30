package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.MyStack
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import java.util.*

class CarFunction(
    private val listValueRetriever: ListValueRetriever
): Function {
    override fun evaluate(
        params: MyStack<NodeV2>
    ): NodeV2 {
        val first = params.pop()

        val firstExpressionListNode = listValueRetriever.retrieveListValue(
            first,
            FunctionNameConstants.CAR
        )
        return firstExpressionListNode.children[0]
    }
}