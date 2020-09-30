package com.soapdogg.lispInterpreter.printer;

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants;
import com.soapdogg.lispInterpreter.constants.TokenValueConstants;
import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer;
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
