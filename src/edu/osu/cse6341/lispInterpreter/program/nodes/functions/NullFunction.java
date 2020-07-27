package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class NullFunction extends BaseFunction implements LispFunction {

	public NullFunction(){}

	private NullFunction(Node params) {
	    super(params);
	}

    @Override
	public Node evaluate() throws Exception{
        assertLengthIsAsExpected(params.getLength());
        Node evaluatedResult = params.evaluate(true);
        boolean result = Node.equalsNil(evaluatedResult.getValue());
		return new AtomNode(result);
	}

    @Override
	public BaseFunction newInstance(Node params){
		return new NullFunction(params);
	}

    @Override
    String getFunctionName() {
        return "NULL";
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
        return new NullFunction(node);
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
