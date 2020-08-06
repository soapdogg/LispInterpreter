package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.singleton.DeterminerSingleton;
import edu.osu.cse6341.lispInterpreter.valueretriver.AtomicValueRetriever;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor(staticName = "newInstance")
public class DefunFunction implements LispFunction {

    private static final Set<String> invalidFunctionNames;

    static {
        invalidFunctionNames = new HashSet<>();
        invalidFunctionNames.add("ATOM");
        invalidFunctionNames.add("CAR");
        invalidFunctionNames.add("CDR");
        invalidFunctionNames.add("COND");
        invalidFunctionNames.add("CONS");
        invalidFunctionNames.add("DEFUN");
        invalidFunctionNames.add("EQ");
        invalidFunctionNames.add("GREATER");
        invalidFunctionNames.add("INT");
        invalidFunctionNames.add("LESS");
        invalidFunctionNames.add("MINUS");
        invalidFunctionNames.add("NULL");
        invalidFunctionNames.add("PLUS");
        invalidFunctionNames.add("QUOTE");
        invalidFunctionNames.add("TIMES");
        invalidFunctionNames.add("T");
        invalidFunctionNames.add("NIL");
    }

    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final FunctionLengthAsserter functionLengthAsserter;
    private final AtomicValueRetriever atomicValueRetriever;
    private final ListValueRetriever listValueRetriever;
    private final Environment environment;

    private void assertFunctionNameIsValid(String functionName) throws Exception{
        if(isInvalidName(functionName))
            throw new Exception("Error! Invalid function name: " + functionName + "\n");
    }

    private List<String> getFormalParameters(LispNode formalParametersNode) throws Exception{
        List<String> formalParameters = new ArrayList<>();
        boolean hasNext = expressionNodeDeterminer.isExpressionNode(formalParametersNode);
        int counter = 1;
        while(hasNext){
            ExpressionNode temp = listValueRetriever.retrieveListValue(
                formalParametersNode,
                FunctionNameConstants.DEFUN
            );
            LispNode formalNode = temp.getAddress();
            String formalId = atomicValueRetriever.retrieveAtomicValue(
                formalNode,
                counter,
                FunctionNameConstants.DEFUN
            );
            assertFormalNameIsValid(formalParameters, formalId);
            formalParameters.add(formalId);
            formalParametersNode = temp.getData();
            ++counter;
            hasNext = expressionNodeDeterminer.isExpressionNode(formalParametersNode);
        }
        return formalParameters;
    }

    private static void assertFormalNameIsValid(List<String> formalParameters, String formalId) throws Exception{
        if(isInvalidName(formalId))
            throw new Exception("Error! Invalid formal id: " + formalId + "\n");
        if(formalParameters.contains(formalId))
            throw new Exception("Error! Duplicate formal id: " + formalId +"\n");

    }

    private static boolean isInvalidName(String name){
        return invalidFunctionNames.contains(name) || name.matches("-?[1-9][0-9]*|0");
    }

    @Override
    public LispNode evaluateLispFunction(final LispNode params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.DEFUN,
            FunctionLengthConstants.FOUR,
            params
        );

        ExpressionNode functionNameNode = listValueRetriever.retrieveListValue(
            params,
            FunctionNameConstants.DEFUN
        );
        String functionName = atomicValueRetriever.retrieveAtomicValue(
            functionNameNode.getAddress(),
            1,
            FunctionNameConstants.DEFUN
        );
        assertFunctionNameIsValid(functionName);

        LispNode functionNameNodeData = functionNameNode.getData();
        ExpressionNode tempNode = listValueRetriever.retrieveListValue(
            functionNameNodeData,
            FunctionNameConstants.DEFUN
        );
        List<String> formalParameters;
        if (!expressionNodeDeterminer.isExpressionNode(tempNode.getAddress())) {
            formalParameters = Collections.emptyList();
        } else {
            ExpressionNode formalParametersNode = listValueRetriever.retrieveListValue(
                tempNode.getAddress(),
                FunctionNameConstants.DEFUN
            );
            formalParameters = getFormalParameters(formalParametersNode);
        }
        ExpressionNode temp = listValueRetriever.retrieveListValue(
            functionNameNodeData,
            FunctionNameConstants.DEFUN
        );

        LispNode body = temp.getData();

        UserDefinedFunction userDefinedFunction = UserDefinedFunction.newInstance(
            formalParameters,
            body,
            functionName
        );
        environment.addToFunctions(functionName, userDefinedFunction);
        return null;
    }
}
