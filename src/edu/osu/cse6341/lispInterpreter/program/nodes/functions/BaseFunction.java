package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public abstract class BaseFunction {

    public abstract Node evaluate() throws Exception;

    public abstract BaseFunction newInstance(Node node) throws Exception;

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
        return Integer.parseInt(node.getValueToString());
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
        return node.getValueToString();
    }

    ExpressionNode getListValue(Node node) throws Exception{
        if(!node.isList()) {
            StringBuilder sb = new StringBuilder("Error! Parameter of ");
            sb.append(getFunctionName());
            sb.append(" is not a list.    Actual: ");
            sb.append(node.getValueToString());
            sb.append('\n');
            throw new Exception(sb.toString());
        }
        return (ExpressionNode)node;
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

    private String getActualValue(Node node){
        StringBuilder sb = new StringBuilder();
        if(node.isList()) {
            sb.append('(');
            sb.append(((ExpressionNode)node).getAddress().getValueToString());
            sb.append(')');
        }
        else sb.append(node.getValueToString());
        return sb.toString();
    }
}
