package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;

import java.util.HashMap;
import java.util.Map;


public class Environment {
    private Map<String, UserDefinedFunction> functions;
    private Map<String, LispNode> variables;

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

    public LispNode evaluateFunction(String functionName, LispNode params) throws Exception{
        UserDefinedFunction function = functions.get(functionName);
        return function.evaluate(params);
    }

    Map<String, LispNode> getVariables(){
        return new HashMap<>(variables);
    }

    void unionVariables(Map<String, LispNode> newVariables){
        variables.putAll(newVariables);
    }

    void setVariables(Map<String, LispNode> newVariables){
        variables = newVariables;
    }

    public LispNode getVariableValue(String variableName){
        return variables.get(variableName);
    }
}
