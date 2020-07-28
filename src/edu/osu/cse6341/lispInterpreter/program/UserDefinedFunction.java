package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDefinedFunction {

    private final List<String> formalParameters;
    private final Node body;
    private final String functionName;

    public UserDefinedFunction(String functionName, List<String> formalParameters, Node body){
        this.functionName = functionName;
        this.formalParameters = formalParameters;
        this.body = body;
    }

    public Node evaluate(Node params) throws Exception{
        Environment e = Environment.getEnvironment();
        Map<String, Node> oldVariables = e.getVariables();
        Map<String, Node> newVariables = bindVariablesToParameters(params);
        e.unionVariables(newVariables);
        Node result = body.evaluate(true);
        e.setVariables(oldVariables);
        return result;
    }

    private Map<String, Node> bindVariablesToParameters(Node params) throws Exception{
        assertActualsLengthSameAsFormalsLength(params);
        Map<String, Node> newVariables = new HashMap<>();
        for (String formal: formalParameters) {
            ExpressionNode temp = (ExpressionNode)params;
            newVariables.put(formal, temp.getAddress().evaluate(true));
            params = temp.getData();
        }
        return newVariables;
    }

    private void assertActualsLengthSameAsFormalsLength(Node params) throws Exception{
         if(formalParameters.size() != ((LispNode)params).parameterLength())
            throw new Exception("Length of actual parameters does not match length of formal parameters for function: "
                    + functionName
                    + "\nExpected: " + formalParameters.size()
                    + "    Actual: " + ((LispNode)params).parameterLength()  + "\n");
    }
}
