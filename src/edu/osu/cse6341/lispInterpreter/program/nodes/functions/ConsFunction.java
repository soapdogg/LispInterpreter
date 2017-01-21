package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class ConsFunction extends BaseFunction {

    private Node result;

	public ConsFunction(){}

	private ConsFunction(Node params) throws Exception{
	    assertParametersAreNotEmpty(params);
	    assertLengthIsAsExpected(params.getLength());
	    Node rightSide = ((ExpressionNode)params).getData();
	    result = new ExpressionNode(params.evaluate(), rightSide.evaluate());
    }

    @Override
	public Node evaluate() throws Exception{
        return result;
	}

    @Override
	public BaseFunction newInstance(Node params) throws Exception{
		return new ConsFunction(params);
	}

    @Override
    String getFunctionName() {
        return "CONS";
    }

    @Override
    int getExpectedLength() {
        return 3;
    }

}
