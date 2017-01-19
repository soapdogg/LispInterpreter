package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class AtomFunction extends BaseFunction {

	private int length;
	private Node params;

	public AtomFunction(){}

	private AtomFunction(Node params){
		length = params.getLength();
		this.params = params;
	}

    @Override
	public Node evaluate(){
	    return new AtomNode(!params.evaluate().isList());
	}

    @Override
	public BaseFunction newInstance(Node params){
		return new AtomFunction(params);
	}

}
