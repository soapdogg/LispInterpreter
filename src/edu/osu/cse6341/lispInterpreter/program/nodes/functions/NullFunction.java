package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class NullFunction extends BaseFunction {

	public NullFunction(){}

	private NullFunction(Node params) {
	    super(params);
	}

    @Override
	public Node evaluate() throws Exception{
        assertLengthIsAsExpected(params.getLength());
        ExpressionNode evaluatedResult = getListValue(params.evaluate(true), true);
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

}
