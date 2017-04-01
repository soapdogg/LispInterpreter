package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.types.IType;
import edu.osu.cse6341.lispInterpreter.program.types.ListType;

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

    int getNumericValue(Node node) throws Exception{
        return Integer.parseInt(node.getValue());
    }

    ExpressionNode getListValue(Node node) throws Exception{
        if (Node.equalsNil(node.getValue())) return new ExpressionNode();
        if((!node.isList())) assertTypeIsCorrectError(1, new ListType(2), node.typeCheck());
        return (ExpressionNode) node;
    }

    void assertLengthIsAsExpected(int actual) throws Exception{
        if(actual != getExpectedLength() -1){
            String sb = "TYPE ERROR: Expected length of " + getFunctionName() +
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

    void assertListIsNotEmpty(IType listType) throws Exception{
        if(listType.getLength() == 0) throw new Exception("EMPTY LIST ERROR: Expected list at position [" + 1
                + "] of " + getFunctionName()
                + " function to be non-empty\n");
    }
}
