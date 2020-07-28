package edu.osu.cse6341.lispInterpreter.program.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.program.functions.valueretriver.AtomicValueRetriever;

public class EqFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final AtomicValueRetriever atomicValueRetriever;

	public EqFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
	    atomicValueRetriever = new AtomicValueRetriever();
    }

    @Override
    public Node evaluateLispFunction(Node params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.EQ,
            FunctionLengthConstants.THREE,
            params.getLength()
        );
        Node evaluatedAddress = params.evaluate(true);
        String leftValue = atomicValueRetriever.retrieveAtomicValue(
            evaluatedAddress,
            1,
            FunctionNameConstants.EQ
        );
        Node right = ((ExpressionNode) params).getData();
        Node evaluatedData = right.evaluate(true);
        String rightValue = atomicValueRetriever.retrieveAtomicValue(
            evaluatedData,
            2,
            FunctionNameConstants.EQ
        );
        boolean result = leftValue.equals(rightValue);
        return new AtomNode(result);
    }
}
