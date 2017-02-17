package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public abstract class BaseFunction {

    Node params;

    BaseFunction(){}

    BaseFunction(Node params){
        this.params = params;
    }

    public abstract Node evaluate() throws Exception;
    public abstract BaseFunction newInstance(Node node);
    abstract String getFunctionName();
    abstract int getExpectedLength();

    int getNumericValue(Node node, int position) throws Exception{
        if(!node.isNumeric()) {
            String sb = "Error! Parameter at position: " + position +
                    " of function " +
                    getFunctionName() +
                    " is not numeric!    Actual: " +
                    node.getListNotationToString(true) +
                    '\n';
            throw new Exception(sb);
        }
        return Integer.parseInt(node.getValue());
    }

    String getAtomicValue(Node node, int position) throws Exception{
        if(node.isList()) {
            String sb = "Error! Parameter at position: " + position +
                    " of function " +
                    getFunctionName() +
                    " is not atomic!    Actual: " +
                    node.getListNotationToString(true) +
                    '\n';
            throw new Exception(sb);
        }
        return node.getValue();
    }

    ExpressionNode getListValue(Node node) throws Exception{
        boolean isVariableList = false;
        String temp = node.getValue();
        Environment e = Environment.getEnvironment();
        boolean isVariable = e.isVariableName(temp);
        if(isVariable) isVariableList = e.getVariableValue(temp).isList();
        if((!isVariable && !node.isList() && node.getLength() == 1) || (isVariable && !isVariableList) || (!node.isList() && !Node.equalsNil(node.getValue()))) {
            String sb = "Error! Parameter of " + getFunctionName() +
                    " is not a list.    Actual: " +
                    node.getValue() +
                    '\n';
            throw new Exception(sb);
        }
        Node result = isVariableList ? e.getVariableValue(temp) : node;
        return (ExpressionNode)result;
    }

    void assertLengthIsAsExpected(int actual) throws Exception{
        if(actual != getExpectedLength() -1){
            String sb = "Error! Expected length of " + getFunctionName() +
                    " list is " +
                    getExpectedLength() +
                    "!    Actual: " +
                    (actual + 1) +
                    '\n';
            throw new Exception(sb);
        }
    }
}
