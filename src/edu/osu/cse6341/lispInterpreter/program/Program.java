package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.program.nodes.StartNode;

public class Program implements IParsable, IEvaluatable{
	private static Program singletonProgram;       
	private StartNode rootNode;

	private Program(){
	    reset();
	}

	public static Program getProgram(){
		if(singletonProgram == null) singletonProgram = new Program();
		return singletonProgram;
	}

	@Override
	public void parse(){
		rootNode.parse();
	}

	@Override
	public void evaluate(){
		rootNode.evaluate();
	}

	public String getValue(){
	    return rootNode.getValue();
    }

	public void reset(){
		rootNode = new StartNode();
	}
}
