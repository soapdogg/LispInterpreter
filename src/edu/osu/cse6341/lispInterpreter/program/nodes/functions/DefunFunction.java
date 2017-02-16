package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.UserDefinedFunction;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class DefunFunction extends BaseFunction {

    private static Set<String> invalidFunctionNames;

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

    public DefunFunction(){}

    private DefunFunction(Node params){
        this.params = params;
    }

    @Override
    public Node evaluate() throws Exception {
        assertLengthIsAsExpected(params.getLength());

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
    public BaseFunction newInstance(Node node) {
        return new DefunFunction(node);
    }

    @Override
    String getFunctionName() {
        return "DEFUN";
    }

    @Override
    int getExpectedLength() {
        return 4;
    }


    private void assertFunctionNameIsValid(String functionName) throws Exception{

    }

    private List<String> getFormalParameters(Node formalParametersNode) throws Exception{
        List<String> formalParameters = new ArrayList<>();
        boolean hasNext = true;
        int counter = 1;
        while(hasNext){
            if(formalParametersNode.isList()){
                ExpressionNode temp = getListValue(formalParametersNode);
                Node formalNode = temp.getAddress();
                String formalId = getAtomicValue(formalNode, counter);
                formalParameters.add(formalId);
                formalParametersNode = temp.getData();
            }
            else{
                if (formalParametersNode.getLength() != 0) {
                    String formalId = getAtomicValue(formalParametersNode, counter);
                    formalParameters.add(formalId);
                }
                hasNext = false;
            }
            ++counter;
        }
        return formalParameters;
    }
}
