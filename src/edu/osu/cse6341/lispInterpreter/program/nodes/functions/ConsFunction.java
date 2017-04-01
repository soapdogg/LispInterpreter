package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.types.AnyNatType;
import edu.osu.cse6341.lispInterpreter.program.types.IType;
import edu.osu.cse6341.lispInterpreter.program.types.ListType;

public class ConsFunction extends BaseFunction {

	public ConsFunction(){}

	private ConsFunction(Node params){
	    super(params);
    }

    @Override
	public Node evaluate() throws Exception{
        assertLengthIsAsExpected(params.getLength());
        int leftValue = getNumericValue(params.evaluate(true), 1);
        AtomNode leftSide = new AtomNode(leftValue);
        ExpressionNode rightSide = (ExpressionNode) ((ExpressionNode)params).getData();
        rightSide = getListValue(rightSide.getAddress().evaluate(false), true);
        return new ExpressionNode(leftSide,rightSide);
	}

    @Override
    public IType typeCheck() throws Exception {
        assertLengthIsAsExpected(params.getLength());
        IType paramsType = params.typeCheck();
        assertTypeIsCorrectError(1, new AnyNatType(), paramsType);
        ExpressionNode rightParams = (ExpressionNode) ((ExpressionNode)params).getData();
        IType rightParamType = rightParams.typeCheck();
        assertTypeIsCorrectError(2, new ListType(0), rightParamType);
        return new ListType(rightParamType.getLength()+1);
    }

    @Override
	public BaseFunction newInstance(Node params){
		return new ConsFunction(params);
	}

    @Override
    String getFunctionName() {
        return "CONS";
    }

    @Override
    int getExpectedLength() {
        return 3;
    }

}
