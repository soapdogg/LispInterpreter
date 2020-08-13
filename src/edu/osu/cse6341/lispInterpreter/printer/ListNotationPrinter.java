package edu.osu.cse6341.lispInterpreter.printer;

import edu.osu.cse6341.lispInterpreter.constants.TokenValueConstants;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
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
