package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;

public class AtomFunction extends BaseFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;

	public AtomFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
    }

	private AtomFunction(Node params){
        super(params);
        functionLengthAsserter = new FunctionLengthAsserter();
	}

	public Node evaluate() throws Exception{
        functionLengthAsserter.assertLengthIsAsExpected(
            getFunctionName(),
            expectedParameterLength(),
            params.getLength()
        );
	    Node evaluatedResult = params.evaluate(true);
	    boolean result = !evaluatedResult.isList();
	    return new AtomNode(result);
	}

    @Override
	public BaseFunction newInstance(Node params){
		return new AtomFunction(params);
	}

    @Override
    String getFunctionName() {
        return "ATOM";
    }

    @Override
    public Node evaluateLispFunction() throws Exception {
	    return evaluate();
    }

    @Override
    public LispFunction newFunctionInstance(Node node) {
        return new AtomFunction(node);
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
