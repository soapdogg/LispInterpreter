package com.soapdogg.lispInterpreter.evaluator;

import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.Node;

import java.util.Map;

public class AtomNodeEvaluator {

    public Node evaluate(
        final AtomNode atomNode,
        final Map<String, ? extends Node> variableNameToValueMap
    ) {
        String value = atomNode.getValue();
        if (variableNameToValueMap.containsKey(value)) return variableNameToValueMap.get(value);
        return atomNode;
    }
}
