package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class EqFunction extends BaseFunction {

	public EqFunction(){}

	private EqFunction(Node params){
	    super(params);
	}

    @Override
	public Node evaluate() throws Exception{
        assertLengthIsAsExpected(params.getLength());
        Node right = ((ExpressionNode)params).getData();
        String leftValue = getAtomicValue(params.evaluate(true), true);
        String rightValue = getAtomicValue(right.evaluate(true), false);
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

}
