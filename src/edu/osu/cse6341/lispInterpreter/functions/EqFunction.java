package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.valueretriver.AtomicValueRetriever;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "newInstance")
public class EqFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final NodeEvaluator nodeEvaluator;
    private final AtomicValueRetriever atomicValueRetriever;
    private final ListValueRetriever listValueRetriever;
    private final NodeGenerator nodeGenerator;

    @Override
    public LispNode evaluateLispFunction(final LispNode params, List<UserDefinedFunction> userDefinedFunctions) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.EQ,
            FunctionLengthConstants.THREE,
            params
        );
        LispNode evaluatedAddress = nodeEvaluator.evaluate(
            params,
            userDefinedFunctions,
            true
        );
        String leftValue = atomicValueRetriever.retrieveAtomicValue(
            evaluatedAddress,
            1,
            FunctionNameConstants.EQ
        );
        ExpressionNode expressionNodeParams = listValueRetriever.retrieveListValue(
            params,
            FunctionNameConstants.EQ
        );
        LispNode data = expressionNodeParams.getData();
        LispNode evaluatedData = nodeEvaluator.evaluate(
            data,
            userDefinedFunctions,
            true
        );
        String rightValue = atomicValueRetriever.retrieveAtomicValue(
            evaluatedData,
            2,
            FunctionNameConstants.EQ
        );
        boolean result = leftValue.equals(rightValue);
        return nodeGenerator.generateAtomNode(result);
    }
}
