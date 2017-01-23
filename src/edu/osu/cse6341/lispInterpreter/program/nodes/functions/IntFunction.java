package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class IntFunction extends BaseFunction {

	private boolean result;

	public IntFunction(){}

	private IntFunction(Node params) throws Exception{
        assertLengthIsAsExpected(params.getLength());
        Node evaluatedResult = params.evaluate();
		result = evaluatedResult.isNumeric();
	}

    @Override
	public Node evaluate() throws Exception{
        return new AtomNode(result);
	}

    @Override
	public BaseFunction newInstance(Node params) throws Exception{
		return new IntFunction(params);
	}

    @Override
    String getFunctionName() {
        return "INT";
    }

    @Override
    int getExpectedLength() {
        return 2;
    }

}
