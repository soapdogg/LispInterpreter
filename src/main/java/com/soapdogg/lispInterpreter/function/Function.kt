package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.MyStack

interface Function {

    fun evaluate(
        params: MyStack<NodeV2>
    ): NodeV2
}