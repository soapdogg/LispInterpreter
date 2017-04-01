package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.types.IType;
import edu.osu.cse6341.lispInterpreter.program.types.ListType;

import java.util.concurrent.ExecutionException;

public abstract class BaseFunction {

    Node params;

    BaseFunction(){}

    BaseFunction(Node params){
        this.params = params;
    }

    public abstract Node evaluate() throws Exception;
    public abstract IType typeCheck() throws Exception;
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

    ExpressionNode getListValue(Node node, boolean isEmptyAllowed) throws Exception{
        if (Node.equalsNil(node.getValue()) && isEmptyAllowed) return new ExpressionNode();
        if(Node.equalsNil(node.getValue()) && !isEmptyAllowed){
            String sb = "Error! Empty list not allowed as parameter of " +getFunctionName() + '\n';
            throw new Exception(sb);
        }
        if((!node.isList() && node.getLength() == 1) || (!node.isList() && !Node.equalsNil(node.getValue()))) {
            String sb = "Error! Parameter of " + getFunctionName() +
                    " is not a list.    Actual: " +
                    node.getValue() +
                    '\n';
            throw new Exception(sb);
        }
        return (ExpressionNode) node;
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

    void assertTypeIsCorrectError(int position, IType expected, IType actual) throws Exception{
        if(!expected.equals(actual)){
            throw new Exception("TYPE ERROR: Expected type: " + expected.toString()
                    + " at position [" + position
                    +  "] of " + getFunctionName()
                    + " function.    Actual: " + actual.toString() + "\n");
        }
    }

    void assertListIsNotEmpty(int position, IType listType) throws Exception{
        if(listType.getLength() == 0) throw new Exception("EMPTY LIST ERROR: Expected list at position [" + position
                + "] of " + getFunctionName()
                + " function to be non-empty\n");
    }
}
