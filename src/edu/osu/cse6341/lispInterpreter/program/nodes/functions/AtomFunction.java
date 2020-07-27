package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class AtomFunction extends BaseFunction implements LispFunction {

	public AtomFunction(){}

	private AtomFunction(Node params){
        super(params);
	}

	public Node evaluate() throws Exception{
        assertLengthIsAsExpected(params.getLength());
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

    int getExpectedLength() {
        return 2;
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
        return getExpectedLength();
    }
}
