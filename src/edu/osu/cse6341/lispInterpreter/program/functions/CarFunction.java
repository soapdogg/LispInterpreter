package edu.osu.cse6341.lispInterpreter.program.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.program.functions.valueretriver.ListValueRetriever;

public class CarFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final ListValueRetriever listValueRetriever;

	public CarFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
	    listValueRetriever = new ListValueRetriever();
    }

    @Override
    public Node evaluateLispFunction(Node params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.CAR,
            FunctionLengthConstants.TWO,
            params.getLength()
        );
        Node evaluatedAddress = ((ExpressionNode) params).getAddress().evaluate(false);
        ExpressionNode node = listValueRetriever.retrieveListValue(
            evaluatedAddress,
            FunctionNameConstants.CAR
        );
        return node.getAddress();
    }
}
