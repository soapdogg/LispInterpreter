package edu.osu.cse6341.lispInterpreter.printer;

import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.constants.TokenValueConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ListNotationExpressionNodePrinterTest {

    private ExpressionNodeDeterminer expressionNodeDeterminer;
    private NodeValueComparator nodeValueComparator;
    private AtomNodePrinter atomNodePrinter;

    private ListNotationExpressionNodePrinter listNotationExpressionNodePrinter;

    @BeforeEach
    void setup() {
        expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer.class);
        nodeValueComparator = Mockito.mock(NodeValueComparator.class);
        atomNodePrinter = Mockito.mock(AtomNodePrinter.class);

        listNotationExpressionNodePrinter = ListNotationExpressionNodePrinter.newInstance(
            expressionNodeDeterminer,
            nodeValueComparator,
            atomNodePrinter
        );
    }

    @Test
    void printExpressionNodeInListNotationTest() {
        ExpressionNode rootNode = Mockito.mock(ExpressionNode.class);

        ExpressionNode rootAddress = Mockito.mock(ExpressionNode.class);
        Mockito.when(rootNode.getAddress()).thenReturn(rootAddress);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(rootAddress)).thenReturn(true);

        AtomNode addressAddress = Mockito.mock(AtomNode.class);
        Mockito.when(rootAddress.getAddress()).thenReturn(addressAddress);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(addressAddress)).thenReturn(false);
        String addressAddressValue = "addressAddress";
        Mockito.when(atomNodePrinter.printAtomNode(addressAddress)).thenReturn(addressAddressValue);

        AtomNode addressData = Mockito.mock(AtomNode.class);
        Mockito.when(rootAddress.getData()).thenReturn(addressData);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(addressData)).thenReturn(false);
        String addressDataValue = "addressData";
        Mockito.when(atomNodePrinter.printAtomNode(addressData)).thenReturn(addressDataValue);

        ExpressionNode rootData = Mockito.mock(ExpressionNode.class);
        Mockito.when(rootNode.getData()).thenReturn(rootData);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(rootData)).thenReturn(true);

        AtomNode dataAddress = Mockito.mock(AtomNode.class);
        Mockito.when(rootData.getAddress()).thenReturn(dataAddress);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(dataAddress)).thenReturn(false);
        String dataAddressValue = "dataAddress";
        Mockito.when(atomNodePrinter.printAtomNode(dataAddress)).thenReturn(dataAddressValue);

        AtomNode dataData = Mockito.mock(AtomNode.class);
        Mockito.when(rootData.getData()).thenReturn(dataData);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(dataData)).thenReturn(false);
        String dataDataValue = ReservedValuesConstants.NIL;
        Mockito.when(nodeValueComparator.equalsNil(dataDataValue)).thenReturn(true);
        Mockito.when(atomNodePrinter.printAtomNode(dataData)).thenReturn(dataDataValue);

        String expected =
            String.valueOf(TokenValueConstants.OPEN_PARENTHESES)
            + addressAddressValue
            + ReservedValuesConstants.LIST_DELIMITER
            + addressDataValue
            + TokenValueConstants.CLOSE_PARENTHESES
            + ReservedValuesConstants.SPACE
            + dataAddressValue
            + TokenValueConstants.CLOSE_PARENTHESES;

        String actual = listNotationExpressionNodePrinter.printExpressionNodeInListNotation(rootNode);

        Assertions.assertEquals(expected, actual);
    }
}
