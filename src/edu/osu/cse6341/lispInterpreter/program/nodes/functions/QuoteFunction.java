package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;

public class QuoteFunction extends BaseFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;

	public QuoteFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
    }

    @Override
    public Node evaluateLispFunction(Node params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            getLispFunctionName(),
            expectedParameterLength(),
            params.getLength()
        );
        return ((ExpressionNode) params).getAddress();
    }

    @Override
    public LispFunction newFunctionInstance() {
        return new QuoteFunction();
    }

    @Override
    public String getLispFunctionName() {
        return "QUOTE";
    }

    @Override
    public int expectedParameterLength() {
        return 2;
    }
}
