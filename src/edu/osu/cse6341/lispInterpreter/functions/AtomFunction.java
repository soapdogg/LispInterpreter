package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class AtomFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;

    @Override
    public Node evaluateLispFunction(final LispNode params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.ATOM,
            FunctionLengthConstants.TWO,
            params.parameterLength()
        );
        Node evaluatedResult = params.evaluateLispNode(true);
        boolean result = !((LispNode)evaluatedResult).isNodeList();
        return new AtomNode(result);
    }
}
