package edu.osu.cse6341.lispInterpreter.program.functions.valueretriver;

import edu.osu.cse6341.lispInterpreter.exceptions.NotAListException;
import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.program.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class ListValueRetriever {

    private final NodeValueComparator nodeValueComparator;

    public ListValueRetriever() {
        nodeValueComparator = new NodeValueComparator();
    }

    public ExpressionNode retrieveListValue(
        final Node node,
        final String functionName
    ) throws Exception{
        boolean isVariableList = false;
        String temp = node.getValue();
        Environment e = Environment.getEnvironment();
        boolean isVariable = e.isVariableName(temp);
        if(isVariable) isVariableList = e.getVariableValue(temp).isList();
        if((!isVariable && !node.isList() && node.getLength() == 1) || (isVariable && !isVariableList) || (!node.isList() && !nodeValueComparator.equalsNil(node.getValue()))) {
            String sb = "Error! Parameter of " + functionName +
                " is not a list.    Actual: " +
                node.getValue() +
                '\n';
            throw new NotAListException(sb);
        }
        Node result = isVariableList ? e.getVariableValue(temp) : node;
        return (ExpressionNode)result;
    }
}
