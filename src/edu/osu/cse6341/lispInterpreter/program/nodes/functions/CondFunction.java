package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.program.nodes.functions.valueretriver.ListValueRetriever;

import java.util.ArrayList;
import java.util.List;


public class CondFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final ListValueRetriever listValueRetriever;

	public CondFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
	    listValueRetriever = new ListValueRetriever();
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
                expectedParameterLength(),
                parameter.getData().getLength()
            );
            parameters.add(parameter);
            params = expressionParams.getData();
        }
        for(ExpressionNode parameter: parameters){
            Node booleanResult = parameter.getAddress().evaluate(true);

            if(!Node.equalsNil(booleanResult.getValue()))
                return parameter.getData().evaluate(true);
        }
        throw new Exception("Error! None of the conditions in the COND function evaluated to true.\n");
    }

    @Override
    public LispFunction newFunctionInstance() {
        return new CondFunction();
    }

    @Override
    public int expectedParameterLength() {
        return 2;
    }
}
