package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.Stack

interface Function {

    fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2
}