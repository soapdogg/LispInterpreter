package edu.osu.cse6341.lispInterpreter.evaluator;

import edu.osu.cse6341.lispInterpreter.asserter.AtomRootNodeAsserter;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.evaluator.ProgramEvaluator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Map;

class ProgramEvaluatorTest {

    private List<Node> rootNodes;
    private List<UserDefinedFunction> userDefinedFunctions;
    private Map<String, Node> variableNameToValueMap;

    private ExpressionNodeDeterminer expressionNodeDeterminer;
    private AtomRootNodeAsserter atomRootNodeAsserter;
    private NodeEvaluator nodeEvaluator;

    private ProgramEvaluator programEvaluator;

    @BeforeEach
    void setup() {
        userDefinedFunctions = Collections.emptyList();
        variableNameToValueMap = Collections.emptyMap();

        expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer.class);
        atomRootNodeAsserter = Mockito.mock(AtomRootNodeAsserter.class);
        nodeEvaluator = Mockito.mock(NodeEvaluator.class);

        programEvaluator = ProgramEvaluator.newInstance(
            expressionNodeDeterminer,
            atomRootNodeAsserter,
            nodeEvaluator
        );
    }

    @Test
    void rootNodeIsAnAtomNodeTest() throws Exception {
        AtomNode atomNode = Mockito.mock(AtomNode.class);
        rootNodes = Collections.singletonList(atomNode);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(atomNode)).thenReturn(false);

        Node evaluatedNode = Mockito.mock(Node.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                atomNode,
                userDefinedFunctions,
                variableNameToValueMap,
            false
            )
        ).thenReturn(evaluatedNode);

        List<Node> actual = programEvaluator.evaluate(
            rootNodes,
            userDefinedFunctions,
            variableNameToValueMap
        );

        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(evaluatedNode, actual.get(0));

        Mockito.verify(atomRootNodeAsserter).assertAtomRootNode(atomNode);
    }

    @Test
    void rootNodeIsExpressionNodeTest() throws Exception {
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        rootNodes = Collections.singletonList(expressionNode);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(expressionNode)).thenReturn(true);

        Node evaluatedNode = Mockito.mock(Node.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                expressionNode,
                userDefinedFunctions,
                variableNameToValueMap,
                false
            )
        ).thenReturn(evaluatedNode);

        List<Node> actual = programEvaluator.evaluate(
            rootNodes,
            userDefinedFunctions,
            variableNameToValueMap
        );

        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(evaluatedNode, actual.get(0));

        Mockito.verifyNoInteractions(atomRootNodeAsserter);
    }
}
