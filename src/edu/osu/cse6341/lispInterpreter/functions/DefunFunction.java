package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.asserter.UserDefinedFormalParametersAsserter;
import edu.osu.cse6341.lispInterpreter.asserter.UserDefinedFunctionNameAsserter;
import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.valueretriver.AtomicValueRetriever;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor(staticName = "newInstance")
public class DefunFunction  {

    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final FunctionLengthAsserter functionLengthAsserter;
    private final AtomicValueRetriever atomicValueRetriever;
    private final ListValueRetriever listValueRetriever;
    private final UserDefinedFunctionNameAsserter userDefinedFunctionNameAsserter;
    private final UserDefinedFormalParametersAsserter userDefinedFormalParametersAsserter;
    private final Environment environment;

    private List<String> getFormalParameters(
        LispNode formalParametersNode,
        final Map<String, LispNode> variableNameToValueMap
    ) throws Exception{
        List<String> formalParameters = new ArrayList<>();
        boolean hasNext = expressionNodeDeterminer.isExpressionNode(formalParametersNode);
        int counter = 1;
        while(hasNext){
            ExpressionNode temp = listValueRetriever.retrieveListValue(
                formalParametersNode,
                FunctionNameConstants.DEFUN,
                variableNameToValueMap
            );
            LispNode formalNode = temp.getAddress();
            String formalId = atomicValueRetriever.retrieveAtomicValue(
                formalNode,
                counter,
                FunctionNameConstants.DEFUN
            );
            formalParameters.add(formalId);
            formalParametersNode = temp.getData();
            ++counter;
            hasNext = expressionNodeDeterminer.isExpressionNode(formalParametersNode);
        }
        userDefinedFormalParametersAsserter.assertFormalParameters(formalParameters);
        return formalParameters;
    }

    public UserDefinedFunction evaluateLispFunction(
        final LispNode params
    ) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.DEFUN,
            FunctionLengthConstants.FOUR,
            params
        );

        ExpressionNode functionNameNode = listValueRetriever.retrieveListValue(
            params,
            FunctionNameConstants.DEFUN,
            new HashMap<>()
        );
        String functionName = atomicValueRetriever.retrieveAtomicValue(
            functionNameNode.getAddress(),
            1,
            FunctionNameConstants.DEFUN
        );
        userDefinedFunctionNameAsserter.assertFunctionNameIsValid(functionName);

        LispNode functionNameNodeData = functionNameNode.getData();
        ExpressionNode tempNode = listValueRetriever.retrieveListValue(
            functionNameNodeData,
            FunctionNameConstants.DEFUN,
            new HashMap<>()
        );
        List<String> formalParameters;
        if (!expressionNodeDeterminer.isExpressionNode(tempNode.getAddress())) {
            formalParameters = Collections.emptyList();
        } else {
            ExpressionNode formalParametersNode = listValueRetriever.retrieveListValue(
                tempNode.getAddress(),
                FunctionNameConstants.DEFUN,
                new HashMap<>()
            );
            formalParameters = getFormalParameters(
                formalParametersNode,
                new HashMap<>()
            );
        }
        ExpressionNode temp = listValueRetriever.retrieveListValue(
            functionNameNodeData,
            FunctionNameConstants.DEFUN,
            new HashMap<>()
        );

        LispNode body = temp.getData();

        return UserDefinedFunction.newInstance(
            formalParameters,
            body,
            functionName
        );
    }
}
