package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import edu.osu.cse6341.lispInterpreter.valueretriver.NumericValueRetriever;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class GreaterFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final NodeEvaluator nodeEvaluator;
    private final NumericValueRetriever numericValueRetriever;
    private final ListValueRetriever listValueRetriever;
    private final NodeGenerator nodeGenerator;

    @Override
    public LispNode evaluateLispFunction(final LispNode params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.GREATER,
            FunctionLengthConstants.THREE,
            params.parameterLength()
        );
        LispNode evaluatedAddress = nodeEvaluator.evaluate(
            params,
            true
        );
        int leftValue = numericValueRetriever.retrieveNumericValue(
            evaluatedAddress,
            1,
            FunctionNameConstants.GREATER
        );
        ExpressionNode expressionNodeParams = listValueRetriever.retrieveListValue(
            params,
            FunctionNameConstants.GREATER
        );
        LispNode data = expressionNodeParams.getData();
        LispNode evaluatedData = nodeEvaluator.evaluate(
            data,
            true
        );
        int rightValue = numericValueRetriever.retrieveNumericValue(
            evaluatedData,
            2,
            FunctionNameConstants.GREATER
        );
        boolean result = leftValue > rightValue;
        return nodeGenerator.generateAtomNode(result);
    }
}