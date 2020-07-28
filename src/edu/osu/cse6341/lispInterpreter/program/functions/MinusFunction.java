package edu.osu.cse6341.lispInterpreter.program.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.program.functions.valueretriver.NumericValueRetriever;
import edu.osu.cse6341.lispInterpreter.singleton.AsserterSingleton;

public class MinusFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final NumericValueRetriever numericValueRetriever;

	public MinusFunction(){
	    functionLengthAsserter = AsserterSingleton.INSTANCE.getFunctionLengthAsserter();
	    numericValueRetriever = new NumericValueRetriever();
    }

    @Override
    public Node evaluateLispFunction(final Node params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.MINUS,
            FunctionLengthConstants.THREE,
            params.getLength()
        );
        Node evaluatedAddress = params.evaluate(true);
        int leftValue = numericValueRetriever.retrieveNumericValue(
            evaluatedAddress,
            1,
            FunctionNameConstants.MINUS
        );
        Node right = ((ExpressionNode) params).getData();
        Node evaluatedData = right.evaluate(true);
        int rightValue = numericValueRetriever.retrieveNumericValue(
            evaluatedData,
            2,
            FunctionNameConstants.MINUS
        );
        int result = leftValue - rightValue;
        return new AtomNode(result);
    }
}
