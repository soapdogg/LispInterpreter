package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.StartNode;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;

public class Program implements IEvaluatable, IPrettyPrintable{

	private StartNode rootNode;
	private boolean hasError;
	private String errorMessage;

	public Program(){
        rootNode = new StartNode();
	}


	public void parse(Tokenizer tokenizer){
		rootNode.parse(tokenizer, this);
	}

	@Override
	public Node evaluate(){
		return rootNode.evaluate();
	}

	@Override
	public String getValueToString(){
	    return rootNode.getValueToString();
    }

    @Override
    public String getDotNotationToString(){
	    return rootNode.getDotNotationToString();
    }

    public boolean hasError(){
        return hasError;
    }

    public void markErrorPresent(){
        hasError = true;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }


}
