package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.asserter.CondFunctionParameterAsserter;
import edu.osu.cse6341.lispInterpreter.evaluator.CondFunctionEvaluator;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class CondFunction implements LispFunction {

    private final CondFunctionParameterAsserter condFunctionParameterAsserter;
    private final CondFunctionEvaluator condFunctionEvaluator;

    @Override
    public LispNode evaluateLispFunction(final LispNode params) throws Exception {
        condFunctionParameterAsserter.assertCondFunctionParameters(
            params
        );
        return condFunctionEvaluator.evaluateCondFunction(
            params
        );
    }
}
