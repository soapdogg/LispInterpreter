package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;

public class ConsFunction extends BaseFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;

    public ConsFunction(){
        functionLengthAsserter = new FunctionLengthAsserter();
    }

	private ConsFunction(Node params){
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
        ExpressionNode rightSide = (ExpressionNode) ((ExpressionNode)params).getData();
        return new ExpressionNode(((ExpressionNode) params).getAddress().evaluate(false), rightSide.getAddress().evaluate(
                false));
	}

    @Override
    String getFunctionName() {
        return "CONS";
    }

    @Override
    public Node evaluateLispFunction() throws Exception {
        return evaluate();
    }

    @Override
    public LispFunction newFunctionInstance(Node node) {
        return new ConsFunction(node);
    }

    @Override
    public String getLispFunctionName() {
        return getFunctionName();
    }

    @Override
    public int expectedParameterLength() {
        return 3;
    }
}
