package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;

public class NullFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;

	public NullFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
    }

    @Override
    public Node evaluateLispFunction(Node params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.NULL,
            expectedParameterLength(),
            params.getLength()
        );
        Node evaluatedResult = params.evaluate(true);
        boolean result = Node.equalsNil(evaluatedResult.getValue());
        return new AtomNode(result);
    }

    @Override
    public int expectedParameterLength() {
        return 2;
    }
}
