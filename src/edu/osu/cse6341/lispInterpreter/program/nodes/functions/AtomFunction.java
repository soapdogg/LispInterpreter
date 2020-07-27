package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;

public class AtomFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;

	public AtomFunction(){
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
        boolean result = !evaluatedResult.isList();
        return new AtomNode(result);
    }

    @Override
    public LispFunction newFunctionInstance() {
        return new AtomFunction();
    }

    @Override
    public String getLispFunctionName() {
        return "ATOM";
    }

    @Override
    public int expectedParameterLength() {
        return 2;
    }
}
