package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.program.nodes.functions.valueretriver.AtomicValueRetriever;
import edu.osu.cse6341.lispInterpreter.program.nodes.functions.valueretriver.ListValueRetriever;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

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

    private final FunctionLengthAsserter functionLengthAsserter;
    private final AtomicValueRetriever atomicValueRetriever;
    private final ListValueRetriever listValueRetriever;

    public DefunFunction(){
        functionLengthAsserter = new FunctionLengthAsserter();
        atomicValueRetriever = new AtomicValueRetriever();
        listValueRetriever = new ListValueRetriever();
    }

    private void assertFunctionNameIsValid(String functionName) throws Exception{
        if(isInvalidName(functionName))
            throw new Exception("Error! Invalid function name: " + functionName + "\n");
    }

    private List<String> getFormalParameters(Node formalParametersNode) throws Exception{
        List<String> formalParameters = new ArrayList<>();
        boolean hasNext = formalParametersNode.isList();
        int counter = 1;
        while(hasNext){
            ExpressionNode temp = listValueRetriever.retrieveListValue(
                formalParametersNode,
                FunctionNameConstants.DEFUN
            );
            Node formalNode = temp.getAddress();
            String formalId = atomicValueRetriever.retrieveAtomicValue(
                formalNode,
                counter,
                FunctionNameConstants.DEFUN
            );
            assertFormalNameIsValid(formalParameters, formalId);
            formalParameters.add(formalId);
            formalParametersNode = temp.getData();
            ++counter;
            hasNext = formalParametersNode.isList();
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
    public Node evaluateLispFunction(Node params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.DEFUN,
            expectedParameterLength(),
            params.getLength()
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

        Node functionNameNodeData = functionNameNode.getData();
        ExpressionNode tempNode = listValueRetriever.retrieveListValue(
            functionNameNodeData,
            FunctionNameConstants.DEFUN
        );
        ExpressionNode formalParametersNode = listValueRetriever.retrieveListValue(
            tempNode.getAddress(),
            FunctionNameConstants.DEFUN
        );
        List<String> formalParameters = getFormalParameters(formalParametersNode);
        ExpressionNode temp = listValueRetriever.retrieveListValue(
            functionNameNodeData,
            FunctionNameConstants.DEFUN
        );

        Node body = temp.getData();

        UserDefinedFunction userDefinedFunction = new UserDefinedFunction(functionName, formalParameters, body);
        Environment.getEnvironment().addToFunctions(functionName, userDefinedFunction);
        return null;
    }

    @Override
    public int expectedParameterLength() {
        return 4;
    }
}
