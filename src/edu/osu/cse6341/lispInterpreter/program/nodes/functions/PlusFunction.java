package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class PlusFunction extends BaseFunction implements LispFunction {

	public PlusFunction(){}

	private PlusFunction(Node params){
	    super(params);
	}

    @Override
	public Node evaluate() throws Exception{
        assertLengthIsAsExpected(params.getLength());
        Node right = ((ExpressionNode)params).getData();
        int leftValue = getNumericValue(params.evaluate(true), 1);
        int rightValue = getNumericValue(right.evaluate(true), 2);
        int result = leftValue + rightValue;
		return new AtomNode(result);
	}

    @Override
	public BaseFunction newInstance(Node params){
		return new PlusFunction(params);
	}

    @Override
    String getFunctionName() {
        return "PLUS";
    }

    @Override
    int getExpectedLength() {
        return 3;
    }

    @Override
    public Node evaluateLispFunction() throws Exception {
        return evaluate();
    }

    @Override
    public LispFunction newFunctionInstance(Node node) {
        return new PlusFunction(node);
    }

    @Override
    public String getLispFunctionName() {
        return getFunctionName();
    }

    @Override
    public int expectedParameterLength() {
        return getExpectedLength();
    }
}
