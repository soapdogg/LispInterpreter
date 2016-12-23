package edu.osu.cse6341.lispInterpreter.program.nodes;

public class AtomNode implements IAtomNode{
	
	private String value;

	@Override
	public void parse(){

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
