package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.determiner.NumericStringDeterminer;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.valueretriver.AtomicValueRetriever;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class IntFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final NodeEvaluator nodeEvaluator;
    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final AtomicValueRetriever atomicValueRetriever;
    private final NumericStringDeterminer numericStringDeterminer;
    private final NodeGenerator nodeGenerator;

    @Override
    public LispNode evaluateLispFunction(final LispNode params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.INT,
            FunctionLengthConstants.TWO,
            params
        );
        LispNode evaluatedResult = nodeEvaluator.evaluate(
            params,
            true
        );
        if (expressionNodeDeterminer.isExpressionNode(evaluatedResult)) {
            return nodeGenerator.generateAtomNode(false);
        }
        String value = atomicValueRetriever.retrieveAtomicValue(
            evaluatedResult,
            1,
            FunctionNameConstants.INT
        );
        boolean result = numericStringDeterminer.isStringNumeric(value);
        return nodeGenerator.generateAtomNode(result);
    }
}
