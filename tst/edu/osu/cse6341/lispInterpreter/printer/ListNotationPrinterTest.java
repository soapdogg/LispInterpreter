package edu.osu.cse6341.lispInterpreter.printer;

import edu.osu.cse6341.lispInterpreter.constants.TokenValueConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

class ListNotationPrinterTest {

    private ExpressionNodeDeterminer expressionNodeDeterminer;
    private AtomNodePrinter atomNodePrinter;
    private ListNotationExpressionNodePrinter listNotationExpressionNodePrinter;

    private ListNotationPrinter listNotationPrinter;


    @BeforeEach
    void setup() {
        expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer.class);
        atomNodePrinter = Mockito.mock(AtomNodePrinter.class);
        listNotationExpressionNodePrinter = Mockito.mock(ListNotationExpressionNodePrinter.class);
        listNotationPrinter = ListNotationPrinter.newInstance(
            expressionNodeDeterminer,
            atomNodePrinter,
            listNotationExpressionNodePrinter
        );
    }

    @Test
    void printNonEmptyListOfNodesTest() {
        AtomNode atomNode = Mockito.mock(AtomNode.class);
        Mockito.when(expressionNodeDeterminer.isExpressionNode(atomNode)).thenReturn(false);

        String value = "value";
        Mockito.when(atomNodePrinter.printAtomNode(atomNode)).thenReturn(value);

        List<Node> nodes = Collections.singletonList(atomNode);

        String expected = value + '\n';
        String actual = listNotationPrinter.printInListNotation(nodes);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void printExpressionNodeListTest() {
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        Mockito.when(expressionNodeDeterminer.isExpressionNode(expressionNode)).thenReturn(true);

        String value = "value";
        Mockito.when(listNotationExpressionNodePrinter.printExpressionNodeInListNotation(expressionNode)).thenReturn(value);

        String expected = TokenValueConstants.OPEN_PARENTHESES + value;
        String actual = listNotationPrinter.printInListNotation(expressionNode);

        Assertions.assertEquals(expected, actual);
    }
}
