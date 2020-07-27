package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.program.nodes.functions.valueretriver.NumericValueRetriever;

public class MinusFunction extends BaseFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final NumericValueRetriever numericValueRetriever;

	public MinusFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
	    numericValueRetriever = new NumericValueRetriever();
    }

    @Override
    String getFunctionName() {
        return "MINUS";
    }

    @Override
    public Node evaluateLispFunction(Node params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            getFunctionName(),
            expectedParameterLength(),
            params.getLength()
        );
        Node right = ((ExpressionNode) params).getData();
        int leftValue = numericValueRetriever.getNumericValue(
            params.evaluate(true),
            1,
            getLispFunctionName()
        );
        int rightValue = numericValueRetriever.getNumericValue(
            right.evaluate(true),
            2,
            getLispFunctionName()
        );
        int result = leftValue - rightValue;
        return new AtomNode(result);
    }

    @Override
    public LispFunction newFunctionInstance() {
        return new MinusFunction();
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
