package edu.osu.cse6341.lispInterpreter.program.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.singleton.AsserterSingleton;

public class ConsFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;

    public ConsFunction(){
        functionLengthAsserter = AsserterSingleton.INSTANCE.getFunctionLengthAsserter();
    }

    @Override
    public Node evaluateLispFunction(final Node params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.CONS,
            FunctionLengthConstants.THREE,
            params.getLength()
        );
        ExpressionNode rightSide = (ExpressionNode) ((ExpressionNode) params).getData();
        Node evaluatedAddress =((ExpressionNode) params).getAddress().evaluate(false);
        Node evaluatedData = rightSide.getAddress().evaluate(false);

        return new ExpressionNode(
            evaluatedAddress,
            evaluatedData
        );
    }
}
