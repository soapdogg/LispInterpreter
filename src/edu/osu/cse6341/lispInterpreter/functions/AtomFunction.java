package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "newInstance")
public class AtomFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final NodeEvaluator nodeEvaluator;
    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final NodeGenerator nodeGenerator;

    @Override
    public LispNode evaluateLispFunction(
        final LispNode params,
        List<UserDefinedFunction> userDefinedFunctions
    ) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.ATOM,
            FunctionLengthConstants.TWO,
            params
        );
        LispNode evaluatedResult = nodeEvaluator.evaluate(
            params,
            userDefinedFunctions,
            true
        );
        boolean result = !expressionNodeDeterminer.isExpressionNode(evaluatedResult);
        return nodeGenerator.generateAtomNode(result);
    }
}
