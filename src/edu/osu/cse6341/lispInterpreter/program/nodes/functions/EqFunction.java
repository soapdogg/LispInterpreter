package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;

public class EqFunction extends BaseFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;

	public EqFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
    }

	private EqFunction(Node params){
	    super(params);
	    functionLengthAsserter = new FunctionLengthAsserter();
	}

    @Override
    String getFunctionName() {
        return "EQ";
    }

    @Override
    public Node evaluateLispFunction() throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            getFunctionName(),
            expectedParameterLength(),
            params.getLength()
        );
        Node right = ((ExpressionNode)params).getData();
        String leftValue = getAtomicValue(params.evaluate(true), 1);
        String rightValue = getAtomicValue(right.evaluate(true), 2);
        boolean result = leftValue.equals(rightValue);
        return new AtomNode(result);
    }

    @Override
    public LispFunction newFunctionInstance(Node node) {
        return new EqFunction(node);
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
