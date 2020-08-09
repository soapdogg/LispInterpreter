package edu.osu.cse6341.lispInterpreter.generator;

import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.valueretriver.AtomicValueRetriever;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Map;

class UserDefinedFunctionFormalParameterGeneratorTest {

    private Node formalParametersNode;
    private int parameterCounter;
    private Map<String, Node> variableNameToValueMap;

    private ExpressionNodeDeterminer expressionNodeDeterminer;
    private ListValueRetriever listValueRetriever;
    private AtomicValueRetriever atomicValueRetriever;

    private UserDefinedFunctionFormalParameterGenerator userDefinedFunctionFormalParameterGenerator;

    @BeforeEach
    void setup() {
        parameterCounter = 1;
        variableNameToValueMap = Collections.emptyMap();

        expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer.class);
        listValueRetriever = Mockito.mock(ListValueRetriever.class);
        atomicValueRetriever = Mockito.mock(AtomicValueRetriever.class);

        userDefinedFunctionFormalParameterGenerator = UserDefinedFunctionFormalParameterGenerator.newInstance(
            expressionNodeDeterminer,
            listValueRetriever,
            atomicValueRetriever
        );
    }

    @Test
    void formalParameterIsAtomNodeTest() throws Exception {
        formalParametersNode = Mockito.mock(AtomNode.class);
        Mockito.when(expressionNodeDeterminer.isExpressionNode(formalParametersNode)).thenReturn(false);

        List<String> actual = userDefinedFunctionFormalParameterGenerator.getFormalParameters(
            formalParametersNode,
            parameterCounter,
            variableNameToValueMap
        );

        Assertions.assertTrue(actual.isEmpty());

        Mockito.verifyZeroInteractions(listValueRetriever);
        Mockito.verifyZeroInteractions(atomicValueRetriever);
    }

    @Test
    void formalParameterIsExpressionNodeTest() throws Exception {
        formalParametersNode = Mockito.mock(ExpressionNode.class);
        Mockito.when(expressionNodeDeterminer.isExpressionNode(formalParametersNode)).thenReturn(true);

        ExpressionNode formalParametersExpressionNode = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                formalParametersNode,
                FunctionNameConstants.DEFUN,
                variableNameToValueMap
            )
        ).thenReturn(formalParametersExpressionNode);

        Node formalNode = Mockito.mock(Node.class);
        Mockito.when(formalParametersExpressionNode.getAddress()).thenReturn(formalNode);

        String formalId = "formalId";
        Mockito.when(
            atomicValueRetriever.retrieveAtomicValue(
                formalNode,
                parameterCounter,
                FunctionNameConstants.DEFUN
            )
        ).thenReturn(formalId);

        Node data = Mockito.mock(Node.class);
        Mockito.when(formalParametersExpressionNode.getData()).thenReturn(data);
        Mockito.when(expressionNodeDeterminer.isExpressionNode(data)).thenReturn(false);

        List<String> actual = userDefinedFunctionFormalParameterGenerator.getFormalParameters(
            formalParametersNode,
            parameterCounter,
            variableNameToValueMap
        );

        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(formalId, actual.get(0));

    }
}
