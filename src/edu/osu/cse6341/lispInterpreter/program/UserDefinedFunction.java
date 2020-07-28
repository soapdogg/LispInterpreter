package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDefinedFunction {

    private final List<String> formalParameters;
    private final LispNode body;
    private final String functionName;

    public UserDefinedFunction(String functionName, List<String> formalParameters, LispNode body){
        this.functionName = functionName;
        this.formalParameters = formalParameters;
        this.body = body;
    }

    public LispNode evaluate(LispNode params) throws Exception{
        Environment e = Environment.getEnvironment();
        Map<String, LispNode> oldVariables = e.getVariables();
        Map<String, LispNode> newVariables = bindVariablesToParameters(params);
        e.unionVariables(newVariables);
        LispNode result = body.evaluate(true);
        e.setVariables(oldVariables);
        return result;
    }

    private Map<String, LispNode> bindVariablesToParameters(LispNode params) throws Exception{
        assertActualsLengthSameAsFormalsLength(params);
        Map<String, LispNode> newVariables = new HashMap<>();
        for (String formal: formalParameters) {
            ExpressionNode temp = (ExpressionNode)params;
            newVariables.put(formal, temp.getAddress().evaluate(true));
            params = temp.getData();
        }
        return newVariables;
    }

    private void assertActualsLengthSameAsFormalsLength(LispNode params) throws Exception{
         if(formalParameters.size() != params.parameterLength())
            throw new Exception("Length of actual parameters does not match length of formal parameters for function: "
                    + functionName
                    + "\nExpected: " + formalParameters.size()
                    + "    Actual: " + params.parameterLength()  + "\n");
    }
}
