package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;

public class NullFunction extends BaseFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;

	public NullFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
    }

    @Override
    public Node evaluateLispFunction(Node params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            getLispFunctionName(),
            expectedParameterLength(),
            params.getLength()
        );
        Node evaluatedResult = params.evaluate(true);
        boolean result = Node.equalsNil(evaluatedResult.getValue());
        return new AtomNode(result);
    }

    @Override
    public LispFunction newFunctionInstance() {
        return new NullFunction();
    }

    @Override
    public String getLispFunctionName() {
        return "NULL";
    }

    @Override
    public int expectedParameterLength() {
        return 2;
    }
}
