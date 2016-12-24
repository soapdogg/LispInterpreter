package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;

public class AtomNode implements IAtomNode{
	
	private String value;

	@Override
	public void parse(){
		value = Tokenizer.getTokenizer().getNextToken().toString();
	}

	@Override
	public String toString(){
		return value;
	}

	@Override
	public IExpressionChild newInstance(){
		return new AtomNode();
	}
}
