package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class CdrFunction extends BaseFunction {

	public CdrFunction(){}

	private CdrFunction(Node params){
        super(params);
    }

    @Override
	public Node evaluate() throws Exception{
        assertLengthIsAsExpected(params.getLength());
        ExpressionNode node = getListValue(((ExpressionNode)params).getAddress().evaluate(false));
        return node.getData();
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
