package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.valueretriver.AtomicValueRetriever;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class EqFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final AtomicValueRetriever atomicValueRetriever;

    @Override
    public LispNode evaluateLispFunction(final LispNode params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.EQ,
            FunctionLengthConstants.THREE,
            params.parameterLength()
        );
        LispNode evaluatedAddress = params.evaluateLispNode(true);
        String leftValue = atomicValueRetriever.retrieveAtomicValue(
            evaluatedAddress,
            1,
            FunctionNameConstants.EQ
        );
        LispNode right = ((ExpressionNode) params).getData();
        LispNode evaluatedData = right.evaluate(true);
        String rightValue = atomicValueRetriever.retrieveAtomicValue(
            evaluatedData,
            2,
            FunctionNameConstants.EQ
        );
        boolean result = leftValue.equals(rightValue);
        return new AtomNode(result);
    }
}
