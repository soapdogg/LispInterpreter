package edu.osu.cse6341.lispInterpreter.asserter;

import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.exceptions.NotAListException;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor(staticName = "newInstance")
public class CondFunctionParameterAsserter {

    private final NodeValueComparator nodeValueComparator;
    private final ListValueRetriever listValueRetriever;
    private final FunctionLengthAsserter functionLengthAsserter;

    public void assertCondFunctionParameters(
        final Node params,
        final Map<String, Node> variableNameToValueMap
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
        Node address = expressionNodeParams.getAddress();
        ExpressionNode expressionNodeAddress = listValueRetriever.retrieveListValue(
            address,
            FunctionNameConstants.COND,
            variableNameToValueMap
        );
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.COND,
            FunctionLengthConstants.TWO,
            expressionNodeAddress.getData()
        );
        assertCondFunctionParameters(
            expressionNodeParams.getData(),
            variableNameToValueMap
        );
    }
}
