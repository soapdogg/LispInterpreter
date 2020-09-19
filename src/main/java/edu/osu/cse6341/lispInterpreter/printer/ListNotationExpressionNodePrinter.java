package edu.osu.cse6341.lispInterpreter.printer;

import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.constants.TokenValueConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class ListNotationExpressionNodePrinter {

    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final NodeValueComparator nodeValueComparator;
    private final AtomNodePrinter atomNodePrinter;

    String printExpressionNodeInListNotation(
        final ExpressionNode node
    ) {
        Node address = node.getAddress();
        String addressListNotation;
        boolean isAddressList = expressionNodeDeterminer.isExpressionNode(address);
        if (isAddressList) {
            ExpressionNode expressionNodeAddress = (ExpressionNode)address;
            String addressExpressionNodeValue = printExpressionNodeInListNotation(
                expressionNodeAddress
            );
            addressListNotation = TokenValueConstants.OPEN_PARENTHESES + addressExpressionNodeValue;
        } else {
            addressListNotation = atomNodePrinter.printAtomNode((AtomNode)address);
        }

        Node data = node.getData();
        String dataListNotation;
        boolean isDataList = expressionNodeDeterminer.isExpressionNode(data);
        if (isDataList) {
            ExpressionNode expressionNodeData = (ExpressionNode)data;
            String dataExpressionNodeValue = printExpressionNodeInListNotation(
                expressionNodeData
            );
            dataListNotation = ReservedValuesConstants.SPACE + dataExpressionNodeValue;
        } else {
            String dataAtomNodeValue = atomNodePrinter.printAtomNode((AtomNode)data);
            boolean isDataValueNil = nodeValueComparator.equalsNil(dataAtomNodeValue);
            String dataString = isDataValueNil
                ? ReservedValuesConstants.EMPTY
                : ReservedValuesConstants.LIST_DELIMITER + dataAtomNodeValue;

            dataListNotation = dataString + TokenValueConstants.CLOSE_PARENTHESES;
        }
        return addressListNotation + dataListNotation;
    }
}
