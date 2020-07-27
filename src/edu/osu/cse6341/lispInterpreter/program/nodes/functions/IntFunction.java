package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class IntFunction extends BaseFunction implements LispFunction {

	public IntFunction(){}

	private IntFunction(Node params){
	    super(params);
	}

    @Override
	public Node evaluate() throws Exception{
        assertLengthIsAsExpected(params.getLength());
        Node evaluatedResult = params.evaluate(true);
		boolean result = evaluatedResult.isNumeric();
		return new AtomNode(result);
	}

    @Override
	public BaseFunction newInstance(Node params){
		return new IntFunction(params);
	}

    @Override
    String getFunctionName() {
        return "INT";
    }

    @Override
    int getExpectedLength() {
        return 2;
    }

    @Override
    public Node evaluateLispFunction() throws Exception {
        return evaluate();
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
        return getExpectedLength();
    }
}
