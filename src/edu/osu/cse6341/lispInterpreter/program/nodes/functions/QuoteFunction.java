package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class QuoteFunction extends BaseFunction implements LispFunction {

	public QuoteFunction(){}

	private QuoteFunction(Node params){
	    super(params);
    }

    @Override
	public Node evaluate() throws Exception{
        assertLengthIsAsExpected(params.getLength());
        return ((ExpressionNode)params).getAddress();
	}

    @Override
	public BaseFunction newInstance(Node params){
		return new QuoteFunction(params);
	}

    @Override
    String getFunctionName() {
        return "QUOTE";
    }

    @Override
    int getExpectedLength() {
        return 2;
    }

    @Override
    public Node evaluateLispFunction() throws Exception {
        return evaluate();
    }

    @Override
    public LispFunction newFunctionInstance(Node node) {
        return new QuoteFunction(node);
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
