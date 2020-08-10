package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.asserter.UserDefinedFormalParametersAsserter;
import edu.osu.cse6341.lispInterpreter.asserter.UserDefinedFunctionNameAsserter;
import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.generator.UserDefinedFunctionFormalParameterGenerator;
import edu.osu.cse6341.lispInterpreter.valueretriver.AtomicValueRetriever;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor(staticName = "newInstance")
public class DefunFunction  {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final AtomicValueRetriever atomicValueRetriever;
    private final ListValueRetriever listValueRetriever;
    private final UserDefinedFunctionNameAsserter userDefinedFunctionNameAsserter;
    private final UserDefinedFunctionFormalParameterGenerator userDefinedFunctionFormalParameterGenerator;
    private final UserDefinedFormalParametersAsserter userDefinedFormalParametersAsserter;

    public UserDefinedFunction evaluateLispFunction(
        final Node params
    ) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.DEFUN,
            FunctionLengthConstants.FOUR,
            params
        );

        ExpressionNode functionNameNode = listValueRetriever.retrieveListValue(
            params,
            FunctionNameConstants.DEFUN,
            Collections.emptyMap()
        );
        String functionName = atomicValueRetriever.retrieveAtomicValue(
            functionNameNode.getAddress(),
            FunctionLengthConstants.ONE,
            FunctionNameConstants.DEFUN
        );
        userDefinedFunctionNameAsserter.assertFunctionNameIsValid(functionName);

        Node functionNameNodeData = functionNameNode.getData();
        ExpressionNode tempNode = listValueRetriever.retrieveListValue(
            functionNameNodeData,
            FunctionNameConstants.DEFUN,
            Collections.emptyMap()
        );
        List<String> formalParameters = userDefinedFunctionFormalParameterGenerator.getFormalParameters(
            tempNode.getAddress(),
            FunctionLengthConstants.ONE,
            Collections.emptyMap()
        );
        userDefinedFormalParametersAsserter.assertFormalParameters(formalParameters);

        Node body = tempNode.getData();

        return UserDefinedFunction.newInstance(
            formalParameters,
            body,
            functionName
        );
    }
}
