package com.soapdogg.lispInterpreter.printer;

import com.soapdogg.lispInterpreter.constants.TokenValueConstants;
import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "newInstance")
public class ListNotationPrinter {

    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final AtomNodePrinter atomNodePrinter;
    private final ListNotationExpressionNodePrinter listNotationExpressionNodePrinter;

    public String printInListNotation(
        List<Node> nodes
    ){
        StringBuilder sb = new StringBuilder();
        for (Node node : nodes) {
            String listNotation = printInListNotation(
                node
            );
            sb.append(listNotation);
            sb.append('\n');
        }
        return sb.toString();
    }

    public String printInListNotation(
        final Node node
    ) {
        boolean isExpressionNode = expressionNodeDeterminer.isExpressionNode(node);
        if (isExpressionNode) {
            char result = TokenValueConstants.OPEN_PARENTHESES;
            return result + listNotationExpressionNodePrinter.printExpressionNodeInListNotation(
                (ExpressionNode) node
            );
        }
        return atomNodePrinter.printAtomNode((AtomNode) node);
    }
}
