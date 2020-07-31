package edu.osu.cse6341.lispInterpreter.valueretriver;

import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.determiner.FunctionLengthDeterminer;
import edu.osu.cse6341.lispInterpreter.exceptions.NotAListException;
import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class ListValueRetriever {

    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final NodeValueComparator nodeValueComparator;
    private final FunctionLengthDeterminer functionLengthDeterminer;

    public ExpressionNode retrieveListValue(
        final LispNode node,
        final String functionName
    ) throws Exception{
        boolean isVariableList = false;
        String temp = node.getValue();
        Environment e = Environment.getEnvironment();
        boolean isVariable = e.isVariableName(temp);
        if(isVariable) isVariableList = expressionNodeDeterminer.isExpressionNode(e.getVariableValue(temp));
        int nodeLength = functionLengthDeterminer.determineFunctionLength(node);
        if((!isVariable && !expressionNodeDeterminer.isExpressionNode(node) && nodeLength == 1) || (isVariable && !isVariableList) || (!expressionNodeDeterminer.isExpressionNode(node)
            && !nodeValueComparator.equalsNil(node.getValue()))) {
            String sb = "Error! Parameter of " + functionName +
                " is not a list.    Actual: " +
                node.getValue() +
                '\n';
            throw new NotAListException(sb);
        }
        LispNode result = isVariableList ? e.getVariableValue(temp) : node;
        return (ExpressionNode)result;
    }
}
