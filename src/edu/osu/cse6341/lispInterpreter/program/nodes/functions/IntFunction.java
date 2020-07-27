package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;

public class IntFunction extends BaseFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;

	public IntFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
    }

	private IntFunction(Node params){
	    super(params);
	    functionLengthAsserter = new FunctionLengthAsserter();
	}

    @Override
    String getFunctionName() {
        return "INT";
    }

    @Override
    public Node evaluateLispFunction() throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            getFunctionName(),
            expectedParameterLength(),
            params.getLength()
        );
        Node evaluatedResult = params.evaluate(true);
        boolean result = evaluatedResult.isNumeric();
        return new AtomNode(result);
    }

    @Override
    public LispFunction newFunctionInstance(Node node) {
        return new IntFunction(node);
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
