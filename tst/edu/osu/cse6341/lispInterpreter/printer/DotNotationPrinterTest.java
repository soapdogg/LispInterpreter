package edu.osu.cse6341.lispInterpreter.printer;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

class DotNotationPrinterTest {

    private DotNotationPrinter dotNotationPrinter;

    @BeforeEach
    void setup() {
        dotNotationPrinter = DotNotationPrinter.newInstance();
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
        Mockito.when(atomNode.getValue()).thenReturn(value);

        List<LispNode> nodes = Collections.singletonList(atomNode);

        String expected = value + '\n';
        String actual = dotNotationPrinter.printInDotNotation(nodes);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void printAtomNodeTest() {
        AtomNode atomNode = Mockito.mock(AtomNode.class);

        String value = "value";
        Mockito.when(atomNode.getValue()).thenReturn(value);

        String actual = dotNotationPrinter.printInDotNotation(atomNode);

        Assertions.assertEquals(value, actual);
    }

    @Test
    void printExpressionNodeListTest() {
        AtomNode address = Mockito.mock(AtomNode.class);

        String addressStr = "address";
        Mockito.when(address.getValue()).thenReturn(addressStr);

        AtomNode data = Mockito.mock(AtomNode.class);

        String dataStr = "data";
        Mockito.when(data.getValue()).thenReturn(dataStr);

        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        Mockito.when(expressionNode.getAddress()).thenReturn(address);
        Mockito.when(expressionNode.getData()).thenReturn(data);

        String expected = '(' + addressStr + " . " + dataStr + ')';

        String actual = dotNotationPrinter.printInDotNotation(expressionNode);

        Assertions.assertEquals(expected, actual);
    }
}
