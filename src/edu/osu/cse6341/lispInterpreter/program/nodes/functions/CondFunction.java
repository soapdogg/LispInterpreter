package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

import java.util.ArrayList;
import java.util.List;


public class CondFunction extends BaseFunction {

	public CondFunction(){}

	private CondFunction(Node params){
        super(params);
    }

    @Override
	public Node evaluate() throws Exception{
        List<ExpressionNode> parameters = new ArrayList<>();
        while(params.isList()){
            ExpressionNode expressionParams = (ExpressionNode)params;
            Node tempParameter = expressionParams.getAddress();
            ExpressionNode parameter = getListValue(tempParameter);
            assertLengthIsAsExpected(parameter.getData().getLength());
            parameters.add(parameter);
            params = expressionParams.getData();
        }
	    for(ExpressionNode parameter: parameters){
            Node booleanResult = parameter.getAddress().evaluate(true);

            if(!Node.equalsF(booleanResult.getValue()))
                return parameter.getData().evaluate(true);
        }
        throw new Exception("Error! None of the conditions in the COND function evaluated to true.\n");
	}

    @Override
	public BaseFunction newInstance(Node params){
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
