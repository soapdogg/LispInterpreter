package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.types.*;

public class AtomFunction extends BaseFunction {

	public AtomFunction(){}

	private AtomFunction(Node params){
        super(params);
	}

    @Override
	public Node evaluate() throws Exception{
        assertLengthIsAsExpected(params.getLength());
	    Node evaluatedResult = params.evaluate(true);
	    boolean result = !evaluatedResult.isList();
	    return new AtomNode(result);
	}

    @Override
    public IType typeCheck() throws Exception {
        assertLengthIsAsExpected(params.getLength());
        IType paramType = params.typeCheck();
        return paramType.equals(new ListType(0)) ? new FalseType() : new TrueType();
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
    int getExpectedLength() {
        return 2;
    }

}
