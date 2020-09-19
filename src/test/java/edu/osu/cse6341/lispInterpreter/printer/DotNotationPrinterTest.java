package edu.osu.cse6341.lispInterpreter.printer;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.printer.AtomNodePrinter;
import edu.osu.cse6341.lispInterpreter.printer.DotNotationExpressionNodePrinter;
import edu.osu.cse6341.lispInterpreter.printer.DotNotationPrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

class DotNotationPrinterTest {

    private ExpressionNodeDeterminer expressionNodeDeterminer;
    private AtomNodePrinter atomNodePrinter;
    private DotNotationExpressionNodePrinter dotNotationExpressionNodePrinter;

    private DotNotationPrinter dotNotationPrinter;

    @BeforeEach
    void setup() {
        expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer.class);
        atomNodePrinter = Mockito.mock(AtomNodePrinter.class);
        dotNotationExpressionNodePrinter = Mockito.mock(DotNotationExpressionNodePrinter.class);
        dotNotationPrinter = DotNotationPrinter.newInstance(
            expressionNodeDeterminer,
            atomNodePrinter,
            dotNotationExpressionNodePrinter
        );
    }

    @Test
    void printEmptyListOfNodesTest() {
        String expected = ReservedValuesConstants.NIL + '\n';
        String actual = dotNotationPrinter.printInDotNotation(Collections.emptyList());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void printNonEmptyListOfNodesTest() {
        AtomNode atomNode = Mockito.mock(AtomNode.class);

        String value = "value";
        Mockito.when(atomNodePrinter.printAtomNode(atomNode)).thenReturn(value);

        List<Node> nodes = Collections.singletonList(atomNode);

        String expected = value + '\n';
        String actual = dotNotationPrinter.printInDotNotation(nodes);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void printAtomNodeTest() {
        AtomNode atomNode = Mockito.mock(AtomNode.class);
        Mockito.when(expressionNodeDeterminer.isExpressionNode(atomNode)).thenReturn(false);

        String value = "value";
        Mockito.when(atomNodePrinter.printAtomNode(atomNode)).thenReturn(value);

        String actual = dotNotationPrinter.printInDotNotation(atomNode);

        Assertions.assertEquals(value, actual);
    }

    @Test
    void printExpressionNodeListTest() {
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        Mockito.when(expressionNodeDeterminer.isExpressionNode(expressionNode)).thenReturn(true);

        String expected = "value";
        Mockito.when(dotNotationExpressionNodePrinter.printExpressionNodeInDotNotation(expressionNode)).thenReturn(expected);

        String actual = dotNotationPrinter.printInDotNotation(expressionNode);

        Assertions.assertEquals(expected, actual);
    }
}
