package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "newInstance")
public class CarFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final ListValueRetriever listValueRetriever;
    private final NodeEvaluator nodeEvaluator;

    @Override
    public LispNode evaluateLispFunction(final LispNode params, List<UserDefinedFunction> userDefinedFunctions) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.CAR,
            FunctionLengthConstants.TWO,
            params
        );
        ExpressionNode expressionNodeParams = listValueRetriever.retrieveListValue(
            params,
            FunctionNameConstants.CAR
        );
        LispNode address = expressionNodeParams.getAddress();
        LispNode evaluatedAddress = nodeEvaluator.evaluate(
            address,
            userDefinedFunctions,
            false
        );
        ExpressionNode node = listValueRetriever.retrieveListValue(
            evaluatedAddress,
            FunctionNameConstants.CAR
        );
        return node.getAddress();
    }
}
