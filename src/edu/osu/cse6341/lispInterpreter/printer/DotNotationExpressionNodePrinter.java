package edu.osu.cse6341.lispInterpreter.printer;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.constants.TokenValueConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class DotNotationExpressionNodePrinter {

    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final AtomNodePrinter atomNodePrinter;

    String printExpressionNodeInDotNotation(ExpressionNode node) {
        String addressDotNotation;
        boolean isAddressExpressionNode = expressionNodeDeterminer.isExpressionNode(node.getAddress());
        if (isAddressExpressionNode) addressDotNotation = printExpressionNodeInDotNotation((ExpressionNode)node.getAddress());
        else addressDotNotation = atomNodePrinter.printAtomNode((AtomNode)node.getAddress());

        String dataDotNotation;
        boolean isDataExpressionNode = expressionNodeDeterminer.isExpressionNode(node.getData());
        if (isDataExpressionNode) dataDotNotation = printExpressionNodeInDotNotation((ExpressionNode)node.getData());
        else dataDotNotation = atomNodePrinter.printAtomNode((AtomNode)node.getData());

        return TokenValueConstants.OPEN_PARENTHESES
            + addressDotNotation
            + ReservedValuesConstants.LIST_DELIMITER
            + dataDotNotation
            + TokenValueConstants.CLOSE_PARENTHESES;
    }
}
