package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;

public class CarFunction extends BaseFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;

	public CarFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
    }

	private CarFunction(Node params){
	    super(params);
	    functionLengthAsserter = new FunctionLengthAsserter();
	}

    @Override
	public Node evaluate() throws Exception{
        functionLengthAsserter.assertLengthIsAsExpected(
            getFunctionName(),
            expectedParameterLength(),
            params.getLength()
        );
        ExpressionNode node = getListValue(((ExpressionNode)params).getAddress().evaluate(false));
        return node.getAddress();
	}

    @Override
	public BaseFunction newInstance(Node params){
		return new CarFunction(params);
	}

    @Override
    String getFunctionName() {
        return "CAR";
    }

    @Override
    public Node evaluateLispFunction() throws Exception {
        return evaluate();
    }

    @Override
    public LispFunction newFunctionInstance(Node node) {
        return new CarFunction(node);
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
