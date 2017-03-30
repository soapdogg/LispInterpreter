package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.program.nodes.StartNode;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;

public class Program implements IPrettyPrintable{

	private final StartNode rootNode;

	public Program(){
        rootNode = new StartNode();
	}

	public void parse(Tokenizer tokenizer) throws Exception {
        rootNode.parse(tokenizer);
    }

    public void typeCheck() throws Exception{
	    rootNode.typeCheck(false);
    }

	public void evaluate() throws Exception{
		rootNode.evaluate(true);
	}

	@Override
	public String getListNotationToString(boolean isFirst){
	    return rootNode.getListNotationToString(isFirst);
    }

    @Override
    public String getDotNotationToString(){
	    return rootNode.getDotNotationToString();
    }

    @Override
    public String getTypeToString(){
        return rootNode.getTypeToString();
    }
}
