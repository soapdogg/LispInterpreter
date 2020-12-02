package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.converter.NodeConverter

enum class ConverterSingleton {
    INSTANCE;

    val nodeConverter: NodeConverter = NodeConverter()
}