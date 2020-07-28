package edu.osu.cse6341.lispInterpreter.program.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.program.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.program.functions.valueretriver.ListValueRetriever;

import java.util.ArrayList;
import java.util.List;


public class CondFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final ListValueRetriever listValueRetriever;
    private final NodeValueComparator nodeValueComparator;

	public CondFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
	    listValueRetriever = new ListValueRetriever();
	    nodeValueComparator = new NodeValueComparator();
    }

    @Override
    public Node evaluateLispFunction(Node params) throws Exception {
        List<ExpressionNode> parameters = new ArrayList<>();
        while(params.isList()){
            ExpressionNode expressionParams = (ExpressionNode) params;
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
            params = expressionParams.getData();
        }
        for(ExpressionNode parameter: parameters){
            Node booleanResult = parameter.getAddress().evaluate(true);

            if(!nodeValueComparator.equalsNil(booleanResult.getValue()))
                return parameter.getData().evaluate(true);
        }
        throw new Exception("Error! None of the conditions in the COND function evaluated to true.\n");
    }
}
