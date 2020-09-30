package com.soapdogg.lispInterpreter.valueretriver;

import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer;
import com.soapdogg.lispInterpreter.exceptions.NotAListException;
import com.soapdogg.lispInterpreter.printer.DotNotationPrinter;
import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
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
