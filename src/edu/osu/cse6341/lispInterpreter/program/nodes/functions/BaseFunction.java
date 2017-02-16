package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public abstract class BaseFunction {

    protected Node params;

    protected BaseFunction(){}

    protected BaseFunction(Node params){
        this.params = params;
    }

    public abstract Node evaluate() throws Exception;
    public abstract BaseFunction newInstance(Node node);
    abstract String getFunctionName();
    abstract int getExpectedLength();

    int getNumericValue(Node node, boolean isLeft) throws Exception{
        if(!node.isNumeric()) {
            StringBuilder sb = new StringBuilder("Error! ");
            sb.append(leftOrRight(isLeft));
            sb.append(" side of ");
            sb.append(getFunctionName());
            sb.append(" is not numeric!    Actual: ");
            sb.append(getActualValue(node));
            sb.append('\n');
            throw new Exception(sb.toString());
        }
        return Integer.parseInt(node.getValue());
    }

    String getAtomicValue(Node node, boolean isLeft) throws Exception{
        if(node.isList()) {
            StringBuilder sb = new StringBuilder("Error! ");
            sb.append(leftOrRight(isLeft));
            sb.append(" side of ");
            sb.append(getFunctionName());
            sb.append(" is not atomic!    Actual: ");
            sb.append(getActualValue(node));
            sb.append('\n');
            throw new Exception(sb.toString());
        }
        return node.getValue();
    }

    ExpressionNode getListValue(Node node) throws Exception{
        boolean isVariableList = false;
        String temp = node.getValue();
        Environment e = Environment.getEnvironment();
        boolean isVariable = e.isVariableName(temp);
        if(isVariable) isVariableList = e.getVariableValue(temp).isList();
        if((!isVariable && !node.isList()) || (isVariable && !isVariableList)) {
            StringBuilder sb = new StringBuilder("Error! Parameter of ");
            sb.append(getFunctionName());
            sb.append(" is not a list.    Actual: ");
            sb.append(node.getValue());
            sb.append('\n');
            throw new Exception(sb.toString());
        }
        Node result = isVariableList ? e.getVariableValue(temp) : node;
        return (ExpressionNode)result;
    }

    void assertLengthIsAsExpected(int actual) throws Exception{
        if(actual != getExpectedLength() -1){
            StringBuilder sb = new StringBuilder("Error! Expected length of ");
            sb.append(getFunctionName());
            sb.append(" list is ");
            sb.append(getExpectedLength());
            sb.append("!    Actual: ");
            sb.append((actual + 1));
            sb.append('\n');
            throw new Exception(sb.toString());
        }
    }

    private String leftOrRight(boolean isLeft){
        return isLeft ? "Left" : "Right";
    }

    private String getActualValue(Node node) throws Exception
    {
        StringBuilder sb = new StringBuilder();
        if(node.isList()) {
            sb.append(((ExpressionNode)node).getAddress().evaluate(true).getListNotationToString(true));
        }
        else sb.append(node.getValue());
        return sb.toString();
    }
}
