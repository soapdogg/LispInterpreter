package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

import java.util.HashMap;
import java.util.Map;


public class Environment {
    private Map<String, UserDefinedFunction> functions;
    private Map<String, Node> variables;

    private static Environment singletonEnvironment;

    private Environment(){
        functions = new HashMap<>();
        variables = new HashMap<>();
    }

    public static Environment getEnvironment(){
        if(singletonEnvironment == null) singletonEnvironment = new Environment();
        return singletonEnvironment;
    }

    public boolean isFunctionName(String functionName){
        return functions.containsKey(functionName);
    }

    public boolean isVariableName(String variableName){
        return variables.containsKey(variableName);
    }

    public void addToFunctions(String functionName, UserDefinedFunction userDefinedFunction){
        functions.put(functionName, userDefinedFunction);
    }

    public Node evaluateFunction(String functionName, Node params) throws Exception{
        UserDefinedFunction function = functions.get(functionName);
        return function.evaluate(params);
    }

    public Map<String, Node> getVariables(){
        return new HashMap<>(variables);
    }

    public void unionVariables(Map<String, Node> newVariables){
        variables.putAll(newVariables);
    }

    public void setVariables(Map<String, Node> newVariables){
        variables = newVariables;
    }

    public Node getVariableValue(String variableName){
        return variables.get(variableName);
    }
}
