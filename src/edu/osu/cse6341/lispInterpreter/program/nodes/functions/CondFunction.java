package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

import java.util.ArrayList;
import java.util.List;


public class CondFunction extends BaseFunction {

    private List<ExpressionNode> parameters;

	public CondFunction(){}

	private CondFunction(Node params)throws Exception{
	    assertParametersAreNotEmpty(params);
	    parameters = new ArrayList<>();
	    while(params.isList()){
	        ExpressionNode expressionParams = (ExpressionNode)params;
	        Node tempParameter = expressionParams.getAddress();
	        ExpressionNode parameter = getListValue(tempParameter);
	        assertParametersAreNotEmpty(parameter);
	        assertLengthIsAsExpected(parameter.getData().getLength());
	        parameters.add(parameter);
	        params = expressionParams.getData();
        }
    }

    @Override
	public Node evaluate() throws Exception{
        for(ExpressionNode parameter: parameters){
            if(!parameter.getAddress().evaluate().getValueToString().equals("NIL"))
                return parameter.getData().evaluate();
        }
        throw new Exception("Error! None of the conditions in the COND function evaluated to true.");
	}

    @Override
	public BaseFunction newInstance(Node params) throws Exception{
		return new CondFunction(params);
	}

    @Override
    String getFunctionName() {
        return "COND";
    }

    @Override
    int getExpectedLength() {
        return 2;
    }

}
