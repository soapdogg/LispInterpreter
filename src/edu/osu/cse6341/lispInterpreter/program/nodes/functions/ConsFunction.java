package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;

public class ConsFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;

    public ConsFunction(){
        functionLengthAsserter = new FunctionLengthAsserter();
    }

    @Override
    public Node evaluateLispFunction(Node params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            getLispFunctionName(),
            expectedParameterLength(),
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

    @Override
    public LispFunction newFunctionInstance() {
        return new ConsFunction();
    }

    @Override
    public String getLispFunctionName() {
        return "CONS";
    }

    @Override
    public int expectedParameterLength() {
        return 3;
    }
}
