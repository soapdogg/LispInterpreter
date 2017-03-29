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
        int leftValue = getNumericValue(params.evaluate(true), 1);
        int rightValue = getNumericValue(right.evaluate(true), 2);
        boolean result = leftValue == rightValue;
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
