package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.types.IType;

public class IntFunction extends BaseFunction {

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
    public IType typeCheck() throws Exception {
        return null;
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

}
