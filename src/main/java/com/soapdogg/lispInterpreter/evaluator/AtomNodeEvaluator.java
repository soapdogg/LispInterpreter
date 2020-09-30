package com.soapdogg.lispInterpreter.evaluator;

import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor(staticName = "newInstance")
public class AtomNodeEvaluator {

    public Node evaluate(
        final AtomNode atomNode,
        final Map<String, Node> variableNameToValueMap
    ) {
        String value = atomNode.getValue();
        if (variableNameToValueMap.containsKey(value)) return variableNameToValueMap.get(value);
        return atomNode;
    }
}
