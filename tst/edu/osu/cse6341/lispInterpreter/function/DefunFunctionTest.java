package edu.osu.cse6341.lispInterpreter.function;

import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.asserter.UserDefinedFormalParametersAsserter;
import edu.osu.cse6341.lispInterpreter.asserter.UserDefinedFunctionNameAsserter;
import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.functions.DefunFunction;
import edu.osu.cse6341.lispInterpreter.generator.UserDefinedFunctionFormalParameterGenerator;
import edu.osu.cse6341.lispInterpreter.valueretriver.AtomicValueRetriever;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

class DefunFunctionTest {

    private Node params;

    private FunctionLengthAsserter functionLengthAsserter;
    private AtomicValueRetriever atomicValueRetriever;
    private ListValueRetriever listValueRetriever;
    private UserDefinedFunctionNameAsserter userDefinedFunctionNameAsserter;
    private UserDefinedFunctionFormalParameterGenerator userDefinedFunctionFormalParameterGenerator;
    private UserDefinedFormalParametersAsserter userDefinedFormalParametersAsserter;

    private DefunFunction defunFunction;

    @BeforeEach
    void setup() {
        params = Mockito.mock(Node.class);

        functionLengthAsserter = Mockito.mock(FunctionLengthAsserter.class);
        atomicValueRetriever = Mockito.mock(AtomicValueRetriever.class);
        listValueRetriever = Mockito.mock(ListValueRetriever.class);
        userDefinedFunctionNameAsserter = Mockito.mock(UserDefinedFunctionNameAsserter.class);
        userDefinedFunctionFormalParameterGenerator = Mockito.mock(UserDefinedFunctionFormalParameterGenerator.class);
        userDefinedFormalParametersAsserter = Mockito.mock(UserDefinedFormalParametersAsserter.class);

        defunFunction = DefunFunction.newInstance(
            functionLengthAsserter,
            atomicValueRetriever,
            listValueRetriever,
            userDefinedFunctionNameAsserter,
            userDefinedFunctionFormalParameterGenerator,
            userDefinedFormalParametersAsserter
        );
    }

    @Test
    void defunFunctionTest() throws Exception {
        ExpressionNode functionNameNode = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                params,
                FunctionNameConstants.DEFUN,
                Collections.emptyMap()
            )
        ).thenReturn(functionNameNode);

        Node functionNameNodeAddress = Mockito.mock(Node.class);
        Mockito.when(functionNameNode.getAddress()).thenReturn(functionNameNodeAddress);

        String functionName = "functionName";
        Mockito.when(
            atomicValueRetriever.retrieveAtomicValue(
                functionNameNodeAddress,
                FunctionLengthConstants.ONE,
                FunctionNameConstants.DEFUN
            )
        ).thenReturn(functionName);

        Node functionNameNodeData = Mockito.mock(Node.class);
        Mockito.when(functionNameNode.getData()).thenReturn(functionNameNodeData);

        ExpressionNode tempNode = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                functionNameNodeData,
                FunctionNameConstants.DEFUN,
                Collections.emptyMap()
            )
        ).thenReturn(tempNode);

        Node tempNodeAddress = Mockito.mock(Node.class);
        Mockito.when(tempNode.getAddress()).thenReturn(tempNodeAddress);

        List<String> formalParameters = Collections.emptyList();
        Mockito.when(
            userDefinedFunctionFormalParameterGenerator.getFormalParameters(
                tempNodeAddress,
                FunctionLengthConstants.ONE,
                Collections.emptyMap()
            )
        ).thenReturn(formalParameters);

        Node body = Mockito.mock(Node.class);
        Mockito.when(tempNode.getData()).thenReturn(body);

        UserDefinedFunction actual = defunFunction.evaluateLispFunction(params);

        Assertions.assertEquals(formalParameters, actual.getFormalParameters());
        Assertions.assertEquals(body, actual.getBody());
        Assertions.assertEquals(functionName, actual.getFunctionName());

        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.DEFUN,
            FunctionLengthConstants.FOUR,
            params
        );

        Mockito.verify(userDefinedFunctionNameAsserter).assertFunctionNameIsValid(functionName);

        Mockito.verify(userDefinedFormalParametersAsserter).assertFormalParameters(
            formalParameters
        );
    }
}
