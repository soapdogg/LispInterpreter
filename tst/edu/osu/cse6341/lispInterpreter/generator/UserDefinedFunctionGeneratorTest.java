package edu.osu.cse6341.lispInterpreter.generator;

import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.determiner.UserDefinedFunctionNameDeterminer;
import edu.osu.cse6341.lispInterpreter.functions.DefunFunction;
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
