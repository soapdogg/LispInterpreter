package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class CdrFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final ListValueRetriever listValueRetriever;

    @Override
    public LispNode evaluateLispFunction(final LispNode params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.CDR,
            FunctionLengthConstants.TWO,
            params.parameterLength()
        );
        LispNode evaluatedAddress = ((ExpressionNode) params).getAddress().evaluate(false);
        ExpressionNode node = listValueRetriever.retrieveListValue(
            evaluatedAddress,
            FunctionNameConstants.CDR
        );
        return node.getData();
    }
}
