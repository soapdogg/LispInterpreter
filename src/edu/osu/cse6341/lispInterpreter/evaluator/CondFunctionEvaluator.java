package edu.osu.cse6341.lispInterpreter.evaluator;

import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.exceptions.NotAListException;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor(staticName = "newInstance")
public class CondFunctionEvaluator {

    private final ListValueRetriever listValueRetriever;
    private final NodeEvaluator nodeEvaluator;
    private final NodeValueComparator nodeValueComparator;

    public Node evaluateCondFunction(
        final Node params,
        final List<UserDefinedFunction> userDefinedFunctions,
        final Map<String, Node> variableNameToValueMap
    ) throws Exception {
        if (params instanceof AtomNode) {
            throw new NotAListException("Error! None of the conditions in the COND function evaluated to true.\n");
        }
        ExpressionNode expressionNodeParams = (ExpressionNode)params;
        Node address = expressionNodeParams.getAddress();
        ExpressionNode expressionNodeAddress = listValueRetriever.retrieveListValue(
            address,
            FunctionNameConstants.COND,
            variableNameToValueMap
        );
        Node booleanResult = nodeEvaluator.evaluate(
            expressionNodeAddress.getAddress(),
            userDefinedFunctions,
            variableNameToValueMap,
            true
        );
        if((booleanResult instanceof AtomNode) && !nodeValueComparator.equalsNil(((AtomNode)booleanResult).getValue()))
            return nodeEvaluator.evaluate(
                expressionNodeAddress.getData(),
                userDefinedFunctions,
                variableNameToValueMap,
                true
            );
        return evaluateCondFunction(
            expressionNodeParams.getData(),
            userDefinedFunctions,
            variableNameToValueMap
        );
    }
}
