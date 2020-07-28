package edu.osu.cse6341.lispInterpreter.program.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.singleton.AsserterSingleton;
import edu.osu.cse6341.lispInterpreter.singleton.ComparatorSingleton;

public class NullFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final NodeValueComparator nodeValueComparator;

	public NullFunction(){
	    functionLengthAsserter = AsserterSingleton.INSTANCE.getFunctionLengthAsserter();
	    nodeValueComparator = ComparatorSingleton.INSTANCE.getNodeValueComparator();
    }

    @Override
    public Node evaluateLispFunction(final Node params) throws Exception {
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
