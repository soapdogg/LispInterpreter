package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.types.IType;
import edu.osu.cse6341.lispInterpreter.program.types.ListType;

public class CdrFunction extends BaseFunction {

	public CdrFunction(){}

	private CdrFunction(Node params){
        super(params);
    }

    @Override
	public Node evaluate() throws Exception{
        assertLengthIsAsExpected(params.getLength());
        ExpressionNode node = getListValue(((ExpressionNode)params).getAddress().evaluate(false), false);
        return node.getData();
	}

    @Override
    public IType typeCheck() throws Exception {
        assertLengthIsAsExpected(params.getLength());
        IType paramsType = params.typeCheck();
        assertTypeIsCorrectError(1, new ListType(1), paramsType);
        assertListIsNotEmpty(1, paramsType);
        return new ListType(paramsType.getLength()-1);
    }

    @Override
	public BaseFunction newInstance(Node params){
		return new CdrFunction(params);
	}

    @Override
    String getFunctionName() {
        return "CDR";
    }

    @Override
    int getExpectedLength() {
        return 2;
    }

}
