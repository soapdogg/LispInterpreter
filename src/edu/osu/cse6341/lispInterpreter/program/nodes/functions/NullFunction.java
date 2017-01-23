package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class NullFunction extends BaseFunction {

	private boolean result;

	public NullFunction(){}

	private NullFunction(Node params) throws Exception{
        assertLengthIsAsExpected(params.getLength());
        Node evaluatedResult = params.evaluate();
		result = evaluatedResult.getValueToString().equals("NIL");
	}

    @Override
	public Node evaluate() throws Exception{
		return new AtomNode(result);
	}

    @Override
	public BaseFunction newInstance(Node params) throws Exception{
		return new NullFunction(params);
	}

    @Override
    String getFunctionName() {
        return "NULL";
    }

    @Override
    int getExpectedLength() {
        return 2;
    }

}
