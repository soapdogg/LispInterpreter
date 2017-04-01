package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.types.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CondFunction extends BaseFunction {

    private static Map<IType, IType> typeMap;

	public CondFunction(){}

	private CondFunction(Node params){
        super(params);
    }

    static {
	    typeMap = new HashMap<>();
	    typeMap.put(new AnyNatType(), new AnyNatType());
	    typeMap.put(new AnyBoolType(), new AnyBoolType());
	    typeMap.put(new TrueType(), new AnyBoolType());
	    typeMap.put(new FalseType(), new AnyBoolType());
	    typeMap.put(new ListType(0), new ListType(0));
    }

    @Override
	public Node evaluate() throws Exception{
        List<ExpressionNode> parameters = extractConditionsIntoList();
	    for(ExpressionNode parameter: parameters){
            Node booleanResult = parameter.getAddress().evaluate();

            if(!Node.equalsF(booleanResult.getValue()))
                return parameter.getData().evaluate();
        }
        throw new Exception("Error! None of the conditions in the COND function evaluated to true.\n");
	}

    @Override
    public IType typeCheck() throws Exception {
        List<ExpressionNode> parameters = extractConditionsIntoList();
        IType condType = null;
        int minLength = Integer.MAX_VALUE;
        for(int i = 0; i < parameters.size(); ++i){
            ExpressionNode parameter = parameters.get(i);
            assertLengthIsAsExpected(parameter.getData().getLength());
            IType booleanType = parameter.typeCheck();
            assertParameterIsBooleanType(booleanType, i +1);

            IType resultType = parameter.getData().typeCheck();
            if(condType == null) {
                condType = typeMap.get(resultType);
                minLength = resultType.getLength();
            }
            else assertTypeIsCorrectError(i + 1, typeMap.get(condType), typeMap.get(resultType));

            if(minLength > resultType.getLength()) minLength = resultType.getLength();
        }
        return typeMap.get(condType).equals(new ListType(0)) ? new ListType(minLength) : typeMap.get(condType);
    }

    private void assertParameterIsBooleanType(IType type, int conditionNumber) throws Exception{
        if(type.equals(new ListType(0)) || type.equals(new AnyNatType()))
            throw new Exception("Expected Bool type in COND function, condition number: " + conditionNumber + ".    Actual: " + type.toString() + "\n");
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

    private List<ExpressionNode> extractConditionsIntoList() throws Exception{
        List<ExpressionNode> parameters = new ArrayList<>();
        if(params.getLength() == 0) throw new Exception("EMPTY LIST ERROR: Expected list of conditions of COND function to be non-empty.\n");
        while(params.isList()){
            ExpressionNode expressionParams = getListValue(params);
            Node tempParameter = expressionParams.getAddress();
            ExpressionNode parameter = getListValue(tempParameter);
            parameters.add(parameter);
            params = expressionParams.getData();
        }

        return parameters;
    }
}
