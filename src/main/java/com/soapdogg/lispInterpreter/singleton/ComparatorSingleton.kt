package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator

enum class ComparatorSingleton {
    INSTANCE;

    val nodeValueComparator: NodeValueComparator = NodeValueComparator()
}