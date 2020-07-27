package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public abstract class BaseFunction {

    abstract String getFunctionName();

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
}
