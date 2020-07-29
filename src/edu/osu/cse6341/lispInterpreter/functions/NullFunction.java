package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;
import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class NullFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final NodeValueComparator nodeValueComparator;
    private final NodeGenerator nodeGenerator;

    @Override
    public LispNode evaluateLispFunction(final LispNode params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.NULL,
            FunctionLengthConstants.TWO,
            params.parameterLength()
        );
        LispNode evaluatedResult = ((IEvaluatable)params).evaluate(true);
        boolean result = nodeValueComparator.equalsNil(evaluatedResult.getNodeValue());
        return nodeGenerator.generateAtomNode(result);
    }
}
