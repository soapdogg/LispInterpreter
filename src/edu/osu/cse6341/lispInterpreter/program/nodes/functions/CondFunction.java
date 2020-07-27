package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;

import java.util.ArrayList;
import java.util.List;


public class CondFunction extends BaseFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;

	public CondFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
    }

    @Override
    String getFunctionName() {
        return "COND";
    }

    @Override
    public Node evaluateLispFunction(Node params) throws Exception {
        List<ExpressionNode> parameters = new ArrayList<>();
        while(params.isList()){
            ExpressionNode expressionParams = (ExpressionNode) params;
            Node tempParameter = expressionParams.getAddress();
            ExpressionNode parameter = getListValue(tempParameter);
            functionLengthAsserter.assertLengthIsAsExpected(
                getFunctionName(),
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
    public String getLispFunctionName() {
        return getFunctionName();
    }

    @Override
    public int expectedParameterLength() {
        return 2;
    }
}
