package edu.osu.cse6341.lispInterpreter.valueretriver;

import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.exceptions.NotAListException;
import edu.osu.cse6341.lispInterpreter.printer.DotNotationPrinter;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor(staticName = "newInstance")
public class ListValueRetriever {

    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final DotNotationPrinter dotNotationPrinter;

    public ExpressionNode retrieveListValue(
        final Node node,
        final String functionName,
        final Map<String, Node> variableNameToValueMap
    ) throws Exception{
        boolean isNodeAList = expressionNodeDeterminer.isExpressionNode(node);
        if (isNodeAList) {
            return (ExpressionNode)node;
        }

        String nodeValue = ((AtomNode)node).getValue();
        boolean isVariable = variableNameToValueMap.containsKey(nodeValue);
        if(isVariable) {
            Node result = variableNameToValueMap.get(nodeValue);
            boolean isVariableList = expressionNodeDeterminer.isExpressionNode(result);
            if (isVariableList) return (ExpressionNode) result;
        }

        String sb = "Error! Parameter of " + functionName +
            " is not a list.    Actual: " +
            dotNotationPrinter.printInDotNotation(node) +
            '\n';
        throw new NotAListException(sb);
    }
}
