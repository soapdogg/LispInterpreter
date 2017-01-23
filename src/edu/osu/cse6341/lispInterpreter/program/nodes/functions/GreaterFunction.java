package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class GreaterFunction extends BaseFunction {

	private boolean result;

	public GreaterFunction(){}

	private GreaterFunction(Node params) throws Exception{
	    assertLengthIsAsExpected(params.getLength());
        Node right = ((ExpressionNode)params).getData();
		int leftValue = getNumericValue(params.evaluate(), true);
		int rightValue = getNumericValue(right.evaluate(), false);
		result = leftValue > rightValue;
	}

    @Override
    public Node evaluate() throws Exception{
        return new AtomNode(result);
    }

    @Override
	public BaseFunction newInstance(Node params) throws Exception{
		return new GreaterFunction(params);
	}

    @Override
    String getFunctionName() {
        return "GREATER";
    }

    @Override
    int getExpectedLength() {
        return 3;
    }

}
