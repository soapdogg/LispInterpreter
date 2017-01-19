package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class IntFunction extends BaseFunction {

	private int length;
	private Node params;

	public IntFunction(){}

	private IntFunction(Node params){
		length = params.getLength();
		this.params  = params;
	}

    @Override
	public Node evaluate(){
        return new AtomNode(params.evaluate().isNumeric());
	}

    @Override
	public BaseFunction newInstance(Node params){
		return new IntFunction(params);
	}

}
