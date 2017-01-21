package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class QuoteFunction extends BaseFunction {

    private Node params;

	public QuoteFunction(){}

	private QuoteFunction(Node params) throws Exception{
	    assertParametersAreNotEmpty(params);
	    assertLengthIsAsExpected(params.getLength());
	    this.params = params;
    }

    @Override
	public Node evaluate(){
        return ((ExpressionNode)params).getAddress();
	}

    @Override
	public BaseFunction newInstance(Node params) throws Exception{
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

}
