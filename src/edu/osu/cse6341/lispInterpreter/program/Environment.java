package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

import java.util.HashMap;
import java.util.Map;


public class Environment {
    private static Map<String, UserDefinedFunction> functions;
    private static Map<String, Node> variables;

    static{
        functions = new HashMap<>();
        variables = new HashMap<>();
    }

    private Environment(){}

    public static boolean isFunctionName(String functionName){
        return functions.containsKey(functionName);
    }

    public static boolean isVariableName(String variableName){
        return variables.containsKey(variableName);
    }

    public static void addToFunctions(String functionName, UserDefinedFunction userDefinedFunction){
        functions.put(functionName, userDefinedFunction);
    }
}
