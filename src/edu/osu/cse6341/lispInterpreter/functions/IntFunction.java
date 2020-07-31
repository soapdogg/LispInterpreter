package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.determiner.NumericStringDeterminer;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class IntFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final NodeEvaluator nodeEvaluator;
    private final NumericStringDeterminer numericStringDeterminer;
    private final NodeGenerator nodeGenerator;

    @Override
    public LispNode evaluateLispFunction(final LispNode params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.INT,
            FunctionLengthConstants.TWO,
            params.parameterLength()
        );
        LispNode evaluatedResult = nodeEvaluator.evaluate(
            params,
            true
        );
        boolean result = numericStringDeterminer.isStringNumeric(evaluatedResult.getNodeValue());
        return nodeGenerator.generateAtomNode(result);
    }
}