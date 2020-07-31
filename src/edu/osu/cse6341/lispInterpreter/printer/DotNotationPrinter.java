package edu.osu.cse6341.lispInterpreter.printer;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class DotNotationPrinter {

    public String printInDotNotation(LispNode node) {
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
