package edu.osu.cse6341.lispInterpreter.program.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.singleton.AsserterSingleton;

public class AtomFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;

	public AtomFunction(){
	    functionLengthAsserter = AsserterSingleton.INSTANCE.getFunctionLengthAsserter();
    }

    @Override
    public Node evaluateLispFunction(final Node params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.ATOM,
            FunctionLengthConstants.TWO,
            params.getLength()
        );
        Node evaluatedResult = params.evaluate(true);
        boolean result = !evaluatedResult.isList();
        return new AtomNode(result);
    }
}
