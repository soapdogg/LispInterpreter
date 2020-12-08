package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.converter.NodeToStackConverter

enum class ConverterSingleton {
    INSTANCE;

    val nodeToStackConverter = NodeToStackConverter(
        DeterminerSingleton.INSTANCE.functionLengthDeterminer
    )
}