package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import lombok.Getter;

@Getter
public enum  ComparatorSingleton {
    INSTANCE;

    private final NodeValueComparator nodeValueComparator;

    ComparatorSingleton() {
        nodeValueComparator = NodeValueComparator.newInstance();
    }
}
