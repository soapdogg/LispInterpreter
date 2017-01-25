package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.program.nodes.StartNode;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;

public class Program implements IParsable, IPrettyPrintable{

	private final StartNode rootNode;

	public Program(){
        rootNode = new StartNode();
	}

	public void parse(Tokenizer tokenizer) throws Exception{
		rootNode.parse(tokenizer);
	}

    @Override
    public String getDotNotationToString(){
	    return rootNode.getDotNotationToString();
    }

}
