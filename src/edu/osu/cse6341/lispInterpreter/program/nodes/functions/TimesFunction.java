package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class TimesFunction extends BaseFunction
{
	private int result;

	public TimesFunction(){}

	private TimesFunction(Node params) throws Exception{
	    assertLengthIsAsExpected(params.getLength());
		Node right = ((ExpressionNode)params).getData();
		int leftValue = getNumericValue(params.evaluate(), true);
		int rightValue = getNumericValue(right.evaluate(),false);
		result = leftValue * rightValue;
	}

    @Override
	public Node evaluate() throws Exception{
        return new AtomNode(result);
	}

    @Override
	public BaseFunction newInstance(Node params) throws Exception{
		return new TimesFunction(params);
	}

    @Override
    String getFunctionName() {
        return "TIMES";
    }

    @Override
    int getExpectedLength() {
        return 3;
    }

}
