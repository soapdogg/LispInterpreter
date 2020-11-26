package com.soapdogg.lispInterpreter.functions;

import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.generator.UserDefinedFunctionFormalParameterGenerator;
import com.soapdogg.lispInterpreter.valueretriver.AtomicValueRetriever;
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever;
import com.soapdogg.lispInterpreter.asserter.UserDefinedFormalParametersAsserter;
import com.soapdogg.lispInterpreter.asserter.UserDefinedFunctionNameAsserter;
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants;
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants;
import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter;
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

        return new UserDefinedFunction(
            formalParameters,
            body,
            functionName
        );
    }
}
