package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.program.nodes.functions.valueretriver.AtomicValueRetriever;

public class EqFunction extends BaseFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final AtomicValueRetriever atomicValueRetriever;

	public EqFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
	    atomicValueRetriever = new AtomicValueRetriever();
    }

    @Override
    String getFunctionName() {
        return "EQ";
    }

    @Override
    public Node evaluateLispFunction(Node params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            getFunctionName(),
            expectedParameterLength(),
            params.getLength()
        );
        Node right = ((ExpressionNode) params).getData();
        String leftValue = atomicValueRetriever.retrieveAtomicValue(
            params.evaluate(true),
            1,
            getLispFunctionName()
        );
        String rightValue = atomicValueRetriever.retrieveAtomicValue(
            right.evaluate(true),
            2,
            getLispFunctionName()
        );
        boolean result = leftValue.equals(rightValue);
        return new AtomNode(result);
    }

    @Override
    public LispFunction newFunctionInstance() {
        return new EqFunction();
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
