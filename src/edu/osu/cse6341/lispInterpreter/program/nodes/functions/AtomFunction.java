package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class AtomFunction extends BaseFunction {

	private boolean result;

	public AtomFunction(){}

	private AtomFunction(Node params) throws Exception{
	    assertLengthIsAsExpected(params.getLength());
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

    @Override
    String getFunctionName() {
        return "ATOM";
    }

    @Override
    int getExpectedLength() {
        return 2;
    }

}
