package edu.osu.cse6341.lispInterpreter.program.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import edu.osu.cse6341.lispInterpreter.singleton.AsserterSingleton;
import edu.osu.cse6341.lispInterpreter.singleton.ComparatorSingleton;
import edu.osu.cse6341.lispInterpreter.singleton.ValueRetrieverSingleton;

import java.util.ArrayList;
import java.util.List;


public class CondFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final ListValueRetriever listValueRetriever;
    private final NodeValueComparator nodeValueComparator;

	public CondFunction(){
	    functionLengthAsserter = AsserterSingleton.INSTANCE.getFunctionLengthAsserter();
	    listValueRetriever = ValueRetrieverSingleton.INSTANCE.getListValueRetriever();
	    nodeValueComparator = ComparatorSingleton.INSTANCE.getNodeValueComparator();
    }

    @Override
    public Node evaluateLispFunction(final Node params) throws Exception {
        List<ExpressionNode> parameters = new ArrayList<>();
        Node current = params;
        while(current.isList()){
            ExpressionNode expressionParams = (ExpressionNode) current;
            Node tempParameter = expressionParams.getAddress();
            ExpressionNode parameter = listValueRetriever.retrieveListValue(
                tempParameter,
                FunctionNameConstants.COND
            );
            functionLengthAsserter.assertLengthIsAsExpected(
                FunctionNameConstants.COND,
                FunctionLengthConstants.TWO,
                parameter.getData().getLength()
            );
            parameters.add(parameter);
            current = expressionParams.getData();
        }
        for(ExpressionNode parameter: parameters){
            Node booleanResult = parameter.getAddress().evaluate(true);

            if(!nodeValueComparator.equalsNil(booleanResult.getValue()))
                return parameter.getData().evaluate(true);
        }
        throw new Exception("Error! None of the conditions in the COND function evaluated to true.\n");
    }
}
