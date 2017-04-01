package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.types.AnyBoolType;
import edu.osu.cse6341.lispInterpreter.program.types.AnyNatType;
import edu.osu.cse6341.lispInterpreter.program.types.IType;

public class LessFunction extends BaseFunction {

	public LessFunction(){}

	private LessFunction(Node params){
	    super(params);
	}

    @Override
    public Node evaluate() throws Exception{
        assertLengthIsAsExpected(params.getLength());
        Node right = ((ExpressionNode)params).getData();
        int leftValue = getNumericValue(params.evaluate(true), 1);
        int rightValue = getNumericValue(right.evaluate(true), 2);
        boolean result = leftValue < rightValue;
        return new AtomNode(result);
    }

    @Override
    public IType typeCheck() throws Exception {
        assertLengthIsAsExpected(params.getLength());
        IType paramsType = params.typeCheck();
        assertTypeIsCorrectError(1, new AnyNatType(), paramsType);
        Node right = ((ExpressionNode)params).getData();
        IType rightType = right.typeCheck();
        assertTypeIsCorrectError(2, new AnyNatType(), rightType);
        return new AnyBoolType();
    }

    @Override
	public BaseFunction newInstance(Node params){
		return new LessFunction(params);
	}

    @Override
    String getFunctionName() {
        return "LESS";
    }

    @Override
    int getExpectedLength() {
        return 3;
    }

}
