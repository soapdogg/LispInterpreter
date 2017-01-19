package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public abstract class BaseFunction {

    protected String errorMessage;

	public abstract boolean hasError();

	public abstract Node evaluate();

    public abstract BaseFunction newInstance(Node node);

    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
