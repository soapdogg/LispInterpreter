package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class EqFunction extends BaseFunction implements LispFunction {

	public EqFunction(){}

	private EqFunction(Node params){
	    super(params);
	}

    @Override
	public Node evaluate() throws Exception{
        assertLengthIsAsExpected(params.getLength());
        Node right = ((ExpressionNode)params).getData();
        String leftValue = getAtomicValue(params.evaluate(true), 1);
        String rightValue = getAtomicValue(right.evaluate(true), 2);
        boolean result = leftValue.equals(rightValue);
		return new AtomNode(result);
	}

    @Override
	public BaseFunction newInstance(Node listNode){
		return new EqFunction(listNode);
	}

    @Override
    String getFunctionName() {
        return "EQ";
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
        return new EqFunction(node);
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
