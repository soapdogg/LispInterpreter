package com.soapdogg.lispInterpreter.generator;

import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.functions.DefunFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

class UserDefinedFunctionGeneratorTest {

    private List<Node> defunNodes;

    private DefunFunction defunFunction;
    private UserDefinedFunction userDefinedFunction;

    private UserDefinedFunctionGenerator userDefinedFunctionGenerator;

    @BeforeEach
    void setup() throws Exception {
        ExpressionNode defunNode = Mockito.mock(ExpressionNode.class);
        defunNodes = Collections.singletonList(defunNode);

        Node defunNodeData = Mockito.mock(Node.class);
        Mockito.when(defunNode.getData()).thenReturn(defunNodeData);

        defunFunction = Mockito.mock(DefunFunction.class);
        userDefinedFunction = Mockito.mock(UserDefinedFunction.class);
        Mockito.when(defunFunction.evaluateLispFunction(defunNodeData)).thenReturn(userDefinedFunction);

        userDefinedFunctionGenerator = UserDefinedFunctionGenerator.newInstance(
            defunFunction
        );
    }

    @Test
    void generateUserDefinedFunctionsTest() throws Exception {
        List<UserDefinedFunction> actual = userDefinedFunctionGenerator.generateUserDefinedFunctions(
            defunNodes
        );

        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(userDefinedFunction, actual.get(0));
    }
}
