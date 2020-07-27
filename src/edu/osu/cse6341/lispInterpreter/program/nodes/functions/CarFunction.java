package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;

public class CarFunction extends BaseFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;

	public CarFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
    }

    @Override
    String getFunctionName() {
        return "CAR";
    }

    @Override
    public Node evaluateLispFunction(Node params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            getFunctionName(),
            expectedParameterLength(),
            params.getLength()
        );
        ExpressionNode node = getListValue(((ExpressionNode) params).getAddress().evaluate(false));
        return node.getAddress();
    }

    @Override
    public LispFunction newFunctionInstance() {
        return new CarFunction();
    }

    @Override
    public String getLispFunctionName() {
        return getFunctionName();
    }

    @Override
    public int expectedParameterLength() {
        return 2;
    }
}
