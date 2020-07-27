package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;

public class NullFunction extends BaseFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;

	public NullFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
    }

	private NullFunction(Node params) {
	    super(params);
	    functionLengthAsserter = new FunctionLengthAsserter();
	}

    @Override
    String getFunctionName() {
        return "NULL";
    }

    @Override
    public Node evaluateLispFunction() throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            getFunctionName(),
            expectedParameterLength(),
            params.getLength()
        );
        Node evaluatedResult = params.evaluate(true);
        boolean result = Node.equalsNil(evaluatedResult.getValue());
        return new AtomNode(result);
    }

    @Override
    public LispFunction newFunctionInstance(Node node) {
        return new NullFunction(node);
    }

    @Override
    public String getLispFunctionName() {
        return getFunctionName();
    }

    @Override
    public int expectedParameterLength() {
        return 2;
    }
}
