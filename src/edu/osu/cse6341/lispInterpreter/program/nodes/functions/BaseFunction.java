package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public abstract class BaseFunction {

    public abstract Node evaluate() throws Exception;

    public abstract BaseFunction newInstance(Node node) throws Exception;

    abstract String getFunctionName();

    abstract int getExpectedLength();

    int getNumericValue(Node node, boolean isLeft) throws Exception{
        String leftOrRight = isLeft ? "Left" : "Right";
        if(!node.isNumeric()) {
            String actual = node.isList() ? ((ExpressionNode)node).getAddress().getValueToString() : node.getValueToString();
            throw new Exception("Error! " + leftOrRight + " side of " + getFunctionName() + " is not numeric!    Actual: " + actual + "\n");
        }
        return Integer.parseInt(node.getValueToString());
    }

    String getAtomicValue(Node node, boolean isLeft) throws Exception{
        String leftOrRight = isLeft ? "Left" : "Right";
        if(node.isList()) throw new Exception("Error! " + leftOrRight + " side of " + getFunctionName() + " is not atomic!    Actual: " + node.getValueToString());
        return node.getValueToString();
    }

    ExpressionNode getListValue(Node node) throws Exception{
        if(!node.isList()) throw new Exception("Error! Parameter of " + getFunctionName() + " is not a list.    Actual: " + node.getValueToString());
        return (ExpressionNode)node;
    }

    void assertParametersAreNotEmpty(Node params) throws Exception{
        if(params == null) throw new Exception("Error! No parameters for the " + getFunctionName() + " function!");
    }

    void assertLengthIsAsExpected(int actual) throws Exception{
        if(actual != getExpectedLength() -1) throw new Exception("Error! Expected length of " + getFunctionName() + " list is " + getExpectedLength() + "!    Actual: " + (actual + 1) + "\n");
    }
}
