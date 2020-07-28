package edu.osu.cse6341.lispInterpreter.program.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.asserter.FunctionLengthAsserter;

public class ConsFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;

    public ConsFunction(){
        functionLengthAsserter = new FunctionLengthAsserter();
    }

    @Override
    public Node evaluateLispFunction(Node params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.CONS,
            FunctionLengthConstants.THREE,
            params.getLength()
        );
        ExpressionNode rightSide = (ExpressionNode) ((ExpressionNode) params).getData();
        return new ExpressionNode(
            ((ExpressionNode) params).getAddress().evaluate(false),
            rightSide.getAddress().evaluate(
            false
            )
        );
    }
}
