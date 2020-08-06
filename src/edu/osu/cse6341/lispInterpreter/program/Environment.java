package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.singleton.EvaluatorSingleton;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor(staticName = "newInstance")
public class Environment {
    private Map<String, UserDefinedFunction> functions;
    private Map<String, LispNode> variables;

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
        return EvaluatorSingleton.INSTANCE.getUserDefinedFunctionEvaluator().evaluate(params, function);
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
