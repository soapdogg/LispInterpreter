package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class DefunFunction extends BaseFunction implements LispFunction {

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

    public DefunFunction(){
        functionLengthAsserter = new FunctionLengthAsserter();
    }

    private DefunFunction(Node params){
        this.params = params;
        functionLengthAsserter = new FunctionLengthAsserter();
    }

    @Override
    public Node evaluate() throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            getFunctionName(),
            expectedParameterLength(),
            params.getLength()
        );

        ExpressionNode functionNameNode = getListValue(params);
        String functionName = getAtomicValue(functionNameNode.getAddress(), 1);
        assertFunctionNameIsValid(functionName);

        Node functionNameNodeData = functionNameNode.getData();
        ExpressionNode tempNode = getListValue(functionNameNodeData);
        ExpressionNode formalParametersNode = getListValue(tempNode.getAddress());
        List<String> formalParameters = getFormalParameters(formalParametersNode);
        ExpressionNode temp = getListValue(functionNameNodeData);

        Node body = temp.getData();

        UserDefinedFunction userDefinedFunction = new UserDefinedFunction(functionName, formalParameters, body);
        Environment.getEnvironment().addToFunctions(functionName, userDefinedFunction);
        return null;
    }

    @Override
    String getFunctionName() {
        return "DEFUN";
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
            ExpressionNode temp = getListValue(formalParametersNode);
            Node formalNode = temp.getAddress();
            String formalId = getAtomicValue(formalNode, counter);
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
    public Node evaluateLispFunction() throws Exception {
        return evaluate();
    }

    @Override
    public LispFunction newFunctionInstance(Node node) {
        return new DefunFunction(node);
    }

    @Override
    public String getLispFunctionName() {
        return getFunctionName();
    }

    @Override
    public int expectedParameterLength() {
        return 4;
    }
}
