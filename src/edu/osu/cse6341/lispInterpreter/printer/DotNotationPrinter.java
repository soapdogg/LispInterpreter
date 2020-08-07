package edu.osu.cse6341.lispInterpreter.printer;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "newInstance")
public class DotNotationPrinter {

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
        if (node instanceof AtomNode) return printAtomNodeInDotNotation((AtomNode)node);
        return printExpressionNodeInDotNotation((ExpressionNode) node);
    }

    private String printAtomNodeInDotNotation(AtomNode node) {
        return node.getValue();
    }

    private String printExpressionNodeInDotNotation(ExpressionNode node) {
        String addressDotNotation = printInDotNotation(node.getAddress());
        String dataDotNotation = printInDotNotation(node.getData());
        return'(' + addressDotNotation + " . " + dataDotNotation + ')';
    }
}
