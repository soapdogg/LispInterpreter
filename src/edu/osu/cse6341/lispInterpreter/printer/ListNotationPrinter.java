package edu.osu.cse6341.lispInterpreter.printer;

import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.determiner.NumericStringDeterminer;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class ListNotationPrinter {

    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final NumericStringDeterminer numericStringDeterminer;
    private final NodeValueComparator nodeValueComparator;

    public String printInListNotation(
        final LispNode node,
        final boolean isFirst
    ) {
        if (node instanceof AtomNode) return printAtomNodeInListNotation((AtomNode)node);
        return printExpressionNodeInListNotation(
            (ExpressionNode) node,
            isFirst
        );
    }

    private String printAtomNodeInListNotation(final AtomNode node) {
        return node.getValue();
    }

    private String printExpressionNodeInListNotation(
        final ExpressionNode node,
        final boolean isFirst
    ) {
        StringBuilder sb = new StringBuilder();
        if(isFirst) sb.append('(');
        LispNode address = node.getAddress();
        sb.append(
            printInListNotation(
                address,
                expressionNodeDeterminer.isExpressionNode(address)
            )
        );

        LispNode data = node.getData();
        String dataListNotation;
        boolean isDataList = expressionNodeDeterminer.isExpressionNode(data);
        if (isDataList) {
            ExpressionNode expressionNodeData = (ExpressionNode)data;
            dataListNotation = ' ' + printExpressionNodeInListNotation(
                expressionNodeData,
                false
            );
        } else {
            AtomNode atomData = ((AtomNode) data);
            String dataString = nodeValueComparator.equalsNil(atomData.getValue())
                ? ""
                : (" . " + printAtomNodeInListNotation(atomData));
            dataListNotation = dataString + ')';
        }
        sb.append(dataListNotation);
        return sb.toString();
    }
}
