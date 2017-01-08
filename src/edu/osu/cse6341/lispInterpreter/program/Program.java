package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.program.nodes.StartNode;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;

public class Program implements IParsable, IEvaluatable{

	private StartNode rootNode;

	public Program(){
        rootNode = new StartNode();
	}

	@Override
	public void parse(Tokenizer tokenizer){
		rootNode.parse(tokenizer);
	}

	@Override
	public void evaluate(){
		rootNode.evaluate();
	}

	public String getValue(){
	    return rootNode.getValue();
    }


}
