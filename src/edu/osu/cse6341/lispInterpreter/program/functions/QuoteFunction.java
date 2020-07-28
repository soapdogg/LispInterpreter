package edu.osu.cse6341.lispInterpreter.program.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.singleton.AsserterSingleton;

public class QuoteFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;

	public QuoteFunction(){
	    functionLengthAsserter = AsserterSingleton.INSTANCE.getFunctionLengthAsserter();
    }

    @Override
    public Node evaluateLispFunction(final Node params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.QUOTE,
            FunctionLengthConstants.TWO,
            params.getLength()
        );
        return ((ExpressionNode) params).getAddress();
    }
}
