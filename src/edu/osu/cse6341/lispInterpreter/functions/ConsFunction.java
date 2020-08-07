package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "newInstance")
public class ConsFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final ListValueRetriever listValueRetriever;
    private final NodeEvaluator nodeEvaluator;
    private final NodeGenerator nodeGenerator;

    @Override
    public LispNode evaluateLispFunction(final LispNode params, List<UserDefinedFunction> userDefinedFunctions) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.CONS,
            FunctionLengthConstants.THREE,
            params
        );
        ExpressionNode expressionNodeParams = listValueRetriever.retrieveListValue(
            params,
            FunctionNameConstants.CONS
        );
        LispNode address = expressionNodeParams.getAddress();
        LispNode evaluatedAddress = nodeEvaluator.evaluate(
            address,
            userDefinedFunctions,
            true
        );
        LispNode data = expressionNodeParams.getData();
        LispNode evaluatedData = nodeEvaluator.evaluate(
            data,
            userDefinedFunctions,
            true
        );
        return nodeGenerator.generateExpressionNode(
            evaluatedAddress,
            evaluatedData
        );
    }
}
