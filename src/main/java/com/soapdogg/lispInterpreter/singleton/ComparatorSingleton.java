package com.soapdogg.lispInterpreter.singleton;

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator;
import lombok.Getter;

@Getter
public enum  ComparatorSingleton {
    INSTANCE;

    private final NodeValueComparator nodeValueComparator;

    ComparatorSingleton() {
        nodeValueComparator = new NodeValueComparator();
    }
}
