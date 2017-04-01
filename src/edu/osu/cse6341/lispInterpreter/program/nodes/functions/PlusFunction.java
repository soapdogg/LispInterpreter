package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.types.AnyNatType;
import edu.osu.cse6341.lispInterpreter.program.types.IType;

public class PlusFunction extends BaseFunction {

	public PlusFunction(){}

	private PlusFunction(Node params){
	    super(params);
	}

    @Override
	public Node evaluate() throws Exception{
        Node right = ((ExpressionNode)params).getData();
        int leftValue = getNumericValue(params.evaluate());
        int rightValue = getNumericValue(right.evaluate());
        int result = leftValue + rightValue;
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
        return new AnyNatType();
    }

    @Override
	public BaseFunction newInstance(Node params){
		return new PlusFunction(params);
	}

    @Override
    String getFunctionName() {
        return "PLUS";
    }

    @Override
    int getExpectedLength() {
        return 3;
    }

}
