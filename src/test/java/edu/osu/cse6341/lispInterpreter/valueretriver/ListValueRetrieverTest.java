package edu.osu.cse6341.lispInterpreter.valueretriver;

import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.exceptions.NotAListException;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import edu.osu.cse6341.lispInterpreter.printer.DotNotationPrinter;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Map;

class ListValueRetrieverTest {

    private Node node;
    private String functionName;
    private Map<String, Node> variableNameToValueMap;

    private ExpressionNodeDeterminer expressionNodeDeterminer;
    private DotNotationPrinter dotNotationPrinter;

    private ListValueRetriever listValueRetriever;

    @BeforeEach
    void setup() {
        functionName = "functionName";
        variableNameToValueMap = Collections.emptyMap();

        expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer.class);
        dotNotationPrinter = Mockito.mock(DotNotationPrinter.class);

        listValueRetriever = ListValueRetriever.newInstance(
            expressionNodeDeterminer,
            dotNotationPrinter
        );
    }

    @Test
    void inputIsAListTest() throws Exception {
        node = Mockito.mock(ExpressionNode.class);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(node)).thenReturn(true);

        ExpressionNode actual = listValueRetriever.retrieveListValue(
            node,
            functionName,
            variableNameToValueMap
        );

        Assertions.assertEquals(node, actual);

        Mockito.verifyNoInteractions(dotNotationPrinter);
    }

    @Test
    void inputIsAVariableListTest() throws Exception {
        node = Mockito.mock(AtomNode.class);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(node)).thenReturn(false);

        String nodeValue = "nodeValue";
        Mockito.when(((AtomNode)node).getValue()).thenReturn(nodeValue);

        Node result = Mockito.mock(ExpressionNode.class);
        variableNameToValueMap = Collections.singletonMap(nodeValue, result);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(result)).thenReturn(true);

        ExpressionNode actual = listValueRetriever.retrieveListValue(
            node,
            functionName,
            variableNameToValueMap
        );

        Assertions.assertEquals(result, actual);
        Mockito.verifyNoInteractions(dotNotationPrinter);
    }

    @Test
    void inputIsVariableButNotListTest() {
        node = Mockito.mock(AtomNode.class);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(node)).thenReturn(false);

        String nodeValue = "nodeValue";
        Mockito.when(((AtomNode)node).getValue()).thenReturn(nodeValue);

        Node result = Mockito.mock(ExpressionNode.class);
        variableNameToValueMap = Collections.singletonMap(nodeValue, result);Mockito.when(expressionNodeDeterminer.isExpressionNode(result)).thenReturn(true);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(result)).thenReturn(false);

        Assertions.assertThrows(
            NotAListException.class,
            () -> listValueRetriever.retrieveListValue(
                node,
                functionName,
                variableNameToValueMap
            )
        );
    }

    @Test
    void inputIsNotAVariableTest() {
        node = Mockito.mock(AtomNode.class);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(node)).thenReturn(false);

        String nodeValue = "nodeValue";
        Mockito.when(((AtomNode)node).getValue()).thenReturn(nodeValue);

        Assertions.assertThrows(
            NotAListException.class,
            () -> listValueRetriever.retrieveListValue(
                node,
                functionName,
                variableNameToValueMap
            )
        );
    }
}
