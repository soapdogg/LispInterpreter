package edu.osu.cse6341.lispInterpreter.printer;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.constants.TokenValueConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "newInstance")
public class DotNotationPrinter {

    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final AtomNodePrinter atomNodePrinter;
    private final DotNotationExpressionNodePrinter dotNotationExpressionNodePrinter;

    public String printInDotNotation(List<Node> nodes) {
        if (nodes.isEmpty()) return ReservedValuesConstants.NIL + '\n';
        StringBuilder sb = new StringBuilder();
        for (Node node : nodes) {
            String dotNotation = printInDotNotation(node);
            sb.append(dotNotation);
            sb.append('\n');
        }
        return sb.toString();
    }

    public String printInDotNotation(Node node) {
        boolean isExpressionNode = expressionNodeDeterminer.isExpressionNode(node);
        if (isExpressionNode) return dotNotationExpressionNodePrinter.printExpressionNodeInDotNotation((ExpressionNode) node);
        return atomNodePrinter.printAtomNode((AtomNode)node);
    }
}
