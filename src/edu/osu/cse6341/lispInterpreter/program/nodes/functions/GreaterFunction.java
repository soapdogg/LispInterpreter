package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class GreaterFunction extends BaseFunction implements LispFunction {

	public GreaterFunction(){}

	private GreaterFunction(Node params){
	    super(params);
	}

    @Override
    public Node evaluate() throws Exception{
        assertLengthIsAsExpected(params.getLength());
        Node right = ((ExpressionNode)params).getData();
        int leftValue = getNumericValue(params.evaluate(true), 1);
        int rightValue = getNumericValue(right.evaluate(true), 2);
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

    @Override
    public Node evaluateLispFunction() throws Exception {
        return null;
    }

    @Override
    public LispFunction newFunctionInstance(Node node) {
        return null;
    }

    @Override
    public String getLispFunctionName() {
        return null;
    }

    @Override
    public int expectedParameterLength() {
        return 0;
    }
}
