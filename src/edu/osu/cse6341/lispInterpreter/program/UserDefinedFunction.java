package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

import java.util.List;

public class UserDefinedFunction {

    private List<String> formalParameters;
    private Node body;
    private String functionName;

    public UserDefinedFunction(String functionName, List<String> formalParameters, Node body){
        this.functionName = functionName;
        this.formalParameters = formalParameters;
        this.body = body;
    }
}
