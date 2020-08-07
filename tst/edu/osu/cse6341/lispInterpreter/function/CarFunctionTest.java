package edu.osu.cse6341.lispInterpreter.function;

import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.functions.CarFunction;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

class CarFunctionTest {

    private LispNode params;
    private List<UserDefinedFunction> userDefinedFunctions;

    private FunctionLengthAsserter functionLengthAsserter;
    private ListValueRetriever listValueRetriever;
    private NodeEvaluator nodeEvaluator;

    private CarFunction carFunction;

    @BeforeEach
    void setup() {
        params = Mockito.mock(ExpressionNode.class);
        userDefinedFunctions = Collections.emptyList();

        functionLengthAsserter = Mockito.mock(FunctionLengthAsserter.class);
        listValueRetriever = Mockito.mock(ListValueRetriever.class);
        nodeEvaluator = Mockito.mock(NodeEvaluator.class);

        carFunction = CarFunction.newInstance(
            functionLengthAsserter,
            listValueRetriever,
            nodeEvaluator
        );
    }

    @Test
    void carFunctionTest() throws Exception {
        ExpressionNode expressionNodeParams = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                params,
                FunctionNameConstants.CAR
            )
        ).thenReturn(expressionNodeParams);

        LispNode address = Mockito.mock(LispNode.class);
        Mockito.when(expressionNodeParams.getAddress()).thenReturn(address);

        LispNode evaluatedAddress = Mockito.mock(LispNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                address,
                userDefinedFunctions,
                false
            )
        ).thenReturn(evaluatedAddress);

        ExpressionNode node = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                evaluatedAddress,
                FunctionNameConstants.CAR
            )
        ).thenReturn(node);

        LispNode expected = Mockito.mock(LispNode.class);
        Mockito.when(node.getAddress()).thenReturn(expected);

        LispNode actual = carFunction.evaluateLispFunction(
            params,
            userDefinedFunctions
        );

        Assertions.assertEquals(expected, actual);
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.CAR,
            FunctionLengthConstants.TWO,
            params
        );
    }
}
