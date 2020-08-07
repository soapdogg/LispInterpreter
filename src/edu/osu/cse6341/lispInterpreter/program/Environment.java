package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor(staticName = "newInstance")
public class Environment {
    private Map<String, LispNode> variables;

    public boolean isVariableName(String variableName){
        return variables.containsKey(variableName);
    }

    public Map<String, LispNode> getVariables(){
        return new HashMap<>(variables);
    }

    public void unionVariables(Map<String, LispNode> newVariables){
        variables.putAll(newVariables);
    }

    public void setVariables(Map<String, LispNode> newVariables){
        variables = newVariables;
    }

    public LispNode getVariableValue(String variableName){
        return variables.get(variableName);
    }
}
