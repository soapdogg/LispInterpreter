package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDefinedFunction {

    private List<String> formalParameters;
    private Node body;
    private String functionName;

    public UserDefinedFunction(String functionName, List<String> formalParameters, Node body){
        this.functionName = functionName;
        this.formalParameters = formalParameters;
        this.body = body;
    }

    public Node evaluate(Node params) throws Exception{
        Map<String, Node> oldVariables = Environment.getEnvironment().getVariables();
        Map<String, Node> newVariables = bindVariablesToParameters(params);
        Environment.getEnvironment().unionVariables(newVariables);
        Node result = body.evaluate(true);
        Environment.getEnvironment().setVariables(oldVariables);
        return result;
    }

    private Map<String, Node> bindVariablesToParameters(Node params) throws Exception{
        if(formalParameters.size() != params.getLength()) throw new Exception("Length of actual parameters does not match length of formal parameters for function: " + functionName);
        Map<String, Node> newVariables = new HashMap<>();
        for (String formal: formalParameters) {
            if(params.isList()){
                ExpressionNode temp = (ExpressionNode)params;
                newVariables.put(formal, temp.getAddress().evaluate(true));
                params = temp.getData();
            }else{
                newVariables.put(formal, params);
            }
        }
        return newVariables;
    }
}
