package edu.osu.cse6341.lispInterpreter.asserter;

import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.exceptions.NotAListException;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class CondFunctionParameterAsserter {

    private final NodeValueComparator nodeValueComparator;
    private final ListValueRetriever listValueRetriever;
    private final FunctionLengthAsserter functionLengthAsserter;

    public void assertCondFunctionParameters(
        LispNode params
    ) throws Exception {
        if (params instanceof AtomNode) {
            AtomNode atomNodeParams = (AtomNode)params;
            if (nodeValueComparator.equalsNil(atomNodeParams.getValue())) {
                return;
            } else {
                throw new NotAListException("Error! COND parameter: " + atomNodeParams.getValue() + " is not a list!");
            }
        }
        ExpressionNode expressionNodeParams = (ExpressionNode) params;
        LispNode address = expressionNodeParams.getAddress();
        ExpressionNode expressionNodeAddress = listValueRetriever.retrieveListValue(
            address,
            FunctionNameConstants.COND
        );
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.COND,
            FunctionLengthConstants.TWO,
            expressionNodeAddress.getData()
        );
        assertCondFunctionParameters(expressionNodeParams.getData());
    }
}
