package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.valueretriver.NumericValueRetriever;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class LessFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final NumericValueRetriever numericValueRetriever;

    @Override
    public LispNode evaluateLispFunction(final LispNode params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.LESS,
            FunctionLengthConstants.THREE,
            params.parameterLength()
        );
        LispNode evaluatedAddress = params.evaluateLispNode(true);
        int leftValue = numericValueRetriever.retrieveNumericValue(
            evaluatedAddress,
            1,
            FunctionNameConstants.LESS
        );
        LispNode right = ((ExpressionNode) params).getData();
        LispNode evaluatedData = right.evaluate(true);
        int rightValue = numericValueRetriever.retrieveNumericValue(
            evaluatedData,
            2,
            FunctionNameConstants.LESS
        );
        boolean result = leftValue < rightValue;
        return new AtomNode(result);
    }
}
