package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.types.AnyNatType;
import edu.osu.cse6341.lispInterpreter.program.types.FalseType;
import edu.osu.cse6341.lispInterpreter.program.types.IType;
import edu.osu.cse6341.lispInterpreter.program.types.TrueType;

public class IntFunction extends BaseFunction {

	public IntFunction(){}

	private IntFunction(Node params){
	    super(params);
	}

    @Override
	public Node evaluate() throws Exception{
        Node evaluatedResult = params.evaluate();
		boolean result = evaluatedResult.isNumeric();
		return new AtomNode(result);
	}

    @Override
    public IType typeCheck() throws Exception {
        assertLengthIsAsExpected(params.getLength());
        IType paramType = params.typeCheck();
	    return paramType.equals(new AnyNatType()) ? new TrueType() : new FalseType();
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
