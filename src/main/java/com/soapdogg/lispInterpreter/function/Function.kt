package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.datamodels.NodeV2
import java.util.*

interface Function {

    fun evaluate(
        params: Stack<NodeV2>,
        variableMap: Map<String, NodeV2>
    ): NodeV2
}