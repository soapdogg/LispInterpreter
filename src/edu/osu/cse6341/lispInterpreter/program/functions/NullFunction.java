package edu.osu.cse6341.lispInterpreter.program.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.program.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.asserter.FunctionLengthAsserter;

public class NullFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final NodeValueComparator nodeValueComparator;

	public NullFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
	    nodeValueComparator = new NodeValueComparator();
    }

    @Override
    public Node evaluateLispFunction(Node params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.NULL,
            FunctionLengthConstants.TWO,
            params.getLength()
        );
        Node evaluatedResult = params.evaluate(true);
        boolean result = nodeValueComparator.equalsNil(evaluatedResult.getValue());
        return new AtomNode(result);
    }
}
