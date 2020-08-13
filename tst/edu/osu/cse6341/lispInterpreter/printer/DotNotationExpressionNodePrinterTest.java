package edu.osu.cse6341.lispInterpreter.printer;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.constants.TokenValueConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DotNotationExpressionNodePrinterTest {

    private ExpressionNodeDeterminer expressionNodeDeterminer;
    private AtomNodePrinter atomNodePrinter;

    private DotNotationExpressionNodePrinter dotNotationExpressionNodePrinter;

    @BeforeEach
    void setup() {
        expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer.class);
        atomNodePrinter = Mockito.mock(AtomNodePrinter.class);

        dotNotationExpressionNodePrinter = DotNotationExpressionNodePrinter.newInstance(
            expressionNodeDeterminer,
            atomNodePrinter
        );
    }

    @Test
    void printExpressionNodeListTest() {
        ExpressionNode address = Mockito.mock(ExpressionNode.class);
        Mockito.when(expressionNodeDeterminer.isExpressionNode(address)).thenReturn(true);

        AtomNode addressAtom = Mockito.mock(AtomNode.class);
        Mockito.when(address.getAddress()).thenReturn(addressAtom);
        Mockito.when(expressionNodeDeterminer.isExpressionNode(addressAtom)).thenReturn(false);

        String addressStr = "address";
        Mockito.when(atomNodePrinter.printAtomNode(addressAtom)).thenReturn(addressStr);

        AtomNode dataAtom = Mockito.mock(AtomNode.class);
        Mockito.when(address.getData()).thenReturn(dataAtom);
        Mockito.when(expressionNodeDeterminer.isExpressionNode(dataAtom)).thenReturn(false);

        String dataStr = "data";
        Mockito.when(atomNodePrinter.printAtomNode(dataAtom)).thenReturn(dataStr);

        ExpressionNode data = Mockito.mock(ExpressionNode.class);
        Mockito.when(expressionNodeDeterminer.isExpressionNode(data)).thenReturn(true);

        AtomNode addressAtom2 = Mockito.mock(AtomNode.class);
        Mockito.when(data.getAddress()).thenReturn(addressAtom2);
        Mockito.when(expressionNodeDeterminer.isExpressionNode(addressAtom2)).thenReturn(false);

        String addressStr2 = "address2";
        Mockito.when(atomNodePrinter.printAtomNode(addressAtom2)).thenReturn(addressStr2);

        AtomNode dataAtom2 = Mockito.mock(AtomNode.class);
        Mockito.when(data.getData()).thenReturn(dataAtom2);
        Mockito.when(expressionNodeDeterminer.isExpressionNode(dataAtom2)).thenReturn(false);

        String dataStr2 = "data";
        Mockito.when(atomNodePrinter.printAtomNode(dataAtom2)).thenReturn(dataStr2);

        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        Mockito.when(expressionNode.getAddress()).thenReturn(address);
        Mockito.when(expressionNode.getData()).thenReturn(data);

        String expected =
            String.valueOf(TokenValueConstants.OPEN_PARENTHESES)
                + TokenValueConstants.OPEN_PARENTHESES
                + addressStr
                + ReservedValuesConstants.LIST_DELIMITER
                + dataStr
                + TokenValueConstants.CLOSE_PARENTHESES
                + ReservedValuesConstants.LIST_DELIMITER
                + TokenValueConstants.OPEN_PARENTHESES
                + addressStr2
                + ReservedValuesConstants.LIST_DELIMITER
                + dataStr2
                + TokenValueConstants.CLOSE_PARENTHESES
                + TokenValueConstants.CLOSE_PARENTHESES;

        String actual = dotNotationExpressionNodePrinter.printExpressionNodeInDotNotation(expressionNode);

        Assertions.assertEquals(expected, actual);
    }
}
