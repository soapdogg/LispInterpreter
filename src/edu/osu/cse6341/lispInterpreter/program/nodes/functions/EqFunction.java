package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class EqFunction extends BaseFunction {

	private boolean result;

	public EqFunction(){}

	private EqFunction(Node params) throws Exception{
	    assertParametersAreNotEmpty(params);
	    assertLengthIsAsExpected(params.getLength());
		Node right = ((ExpressionNode)params).getData();
		String leftValue = getAtomicValue(params.evaluate(), true);
		String rightValue = getAtomicValue(right.evaluate(), false);
		result = leftValue.equals(rightValue);
	}

    @Override
	public Node evaluate() throws Exception{
		return new AtomNode(result);
	}

    @Override
	public BaseFunction newInstance(Node listNode) throws Exception{
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

}
