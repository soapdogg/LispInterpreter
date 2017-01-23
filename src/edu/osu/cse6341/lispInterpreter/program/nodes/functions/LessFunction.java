package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class LessFunction extends BaseFunction {

	private boolean result;

	public LessFunction(){}

	private LessFunction(Node params) throws Exception{
	    assertLengthIsAsExpected(params.getLength());
		Node right = ((ExpressionNode)params).getData();
		int leftValue = getNumericValue(params.evaluate(), true);
		int rightValue = getNumericValue(right.evaluate(), false);
        result = leftValue < rightValue;
	}

    @Override
    public Node evaluate() throws Exception{
        return new AtomNode(result);
    }

    @Override
	public BaseFunction newInstance(Node params) throws Exception{
		return new LessFunction(params);
	}

    @Override
    String getFunctionName() {
        return "LESS";
    }

    @Override
    int getExpectedLength() {
        return 3;
    }

}
