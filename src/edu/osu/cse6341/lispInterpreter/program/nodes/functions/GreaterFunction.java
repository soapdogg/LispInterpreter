package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class GreaterFunction extends BaseFunction {

	public GreaterFunction(){}

	private GreaterFunction(Node params){
	    super(params);
	}

    @Override
    public Node evaluate() throws Exception{
        assertLengthIsAsExpected(params.getLength());
        Node right = ((ExpressionNode)params).getData();
        int leftValue = getNumericValue(params.evaluate(true), true);
        int rightValue = getNumericValue(right.evaluate(true), false);
        boolean result = leftValue > rightValue;
        return new AtomNode(result);
    }

    @Override
	public BaseFunction newInstance(Node params){
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
