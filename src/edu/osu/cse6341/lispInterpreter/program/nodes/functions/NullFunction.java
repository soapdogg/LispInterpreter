package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.types.*;

public class NullFunction extends BaseFunction {

	public NullFunction(){}

	private NullFunction(Node params) {
	    super(params);
	}

    @Override
	public Node evaluate() throws Exception{
        ExpressionNode evaluatedResult = getListValue(params.evaluate());
        boolean result = Node.equalsNil(evaluatedResult.getValue());
		return new AtomNode(result);
	}

    @Override
    public IType typeCheck() throws Exception {
        assertLengthIsAsExpected(params.getLength());
        IType paramType = params.typeCheck();
        assertTypeIsCorrectError(1, new ListType(0), paramType);
        return paramType.getLength() == 0 ? new AnyBoolType() : new FalseType();
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
