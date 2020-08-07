package edu.osu.cse6341.lispInterpreter.printer;

import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "newInstance")
public class ListNotationPrinter {

    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final NodeValueComparator nodeValueComparator;

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
        if (node instanceof AtomNode) return ((AtomNode) node).getValue();
        String result = "(";
        return result + printExpressionNodeInListNotation(
            (ExpressionNode) node
        );
    }

    private String printExpressionNodeInListNotation(
        final ExpressionNode node
    ) {
        StringBuilder sb = new StringBuilder();
        Node address = node.getAddress();

        if (expressionNodeDeterminer.isExpressionNode(address)) {
            sb.append(
                printInListNotation(
                    address
                )
            );
        } else {
            sb.append(
                ((AtomNode) address).getValue()
            );
        }

        Node data = node.getData();
        String dataListNotation;
        boolean isDataList = expressionNodeDeterminer.isExpressionNode(data);
        if (isDataList) {
            ExpressionNode expressionNodeData = (ExpressionNode)data;
            dataListNotation = ' ' + printExpressionNodeInListNotation(
                expressionNodeData
            );
        } else {
            AtomNode atomData = ((AtomNode) data);
            String dataString = nodeValueComparator.equalsNil(atomData.getValue())
                ? ""
                : " . " + atomData.getValue();
            dataListNotation = dataString + ')';
        }
        sb.append(dataListNotation);
        return sb.toString();
    }
}
