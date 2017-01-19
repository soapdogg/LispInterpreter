package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class AtomFunction extends BaseFunction {

	private boolean result;

	public AtomFunction(){}

	private AtomFunction(Node params) throws Exception{
		if(params == null) throw new Exception("Error! No parameters for the ATOM Function");
        if(params.getLength() != 1) throw new Exception("Error! Expected length of ATOM list is: 2    Actual" + params.getLength() + 1);

		Node evaluatedResult = params.evaluate();
		result = !evaluatedResult.isList();
	}

    @Override
	public Node evaluate() throws Exception{
	    return new AtomNode(result);
	}

    @Override
	public BaseFunction newInstance(Node params) throws Exception{
		return new AtomFunction(params);
	}

}
