package com.soapdogg.lispInterpreter.asserter;

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator;
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants;
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants;
import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.exceptions.NotAListException;
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever;
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
