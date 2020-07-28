package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(staticName = "newInstance")
public class CondFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final ListValueRetriever listValueRetriever;
    private final NodeValueComparator nodeValueComparator;

    @Override
    public LispNode evaluateLispFunction(final LispNode params) throws Exception {
        List<ExpressionNode> parameters = new ArrayList<>();
        LispNode current = params;
        while(current.isNodeList()){
            ExpressionNode expressionParams = (ExpressionNode) current;
            LispNode tempParameter = expressionParams.getAddress();
            ExpressionNode parameter = listValueRetriever.retrieveListValue(
                tempParameter,
                FunctionNameConstants.COND
            );
            functionLengthAsserter.assertLengthIsAsExpected(
                FunctionNameConstants.COND,
                FunctionLengthConstants.TWO,
                parameter.getData().parameterLength()
            );
            parameters.add(parameter);
            current = expressionParams.getData();
        }
        for(ExpressionNode parameter: parameters){
            LispNode booleanResult = ((IEvaluatable)parameter.getAddress()).evaluate(true);

            if(!nodeValueComparator.equalsNil(booleanResult.getNodeValue()))
                return ((IEvaluatable)parameter.getData()).evaluate(true);
        }
        throw new Exception("Error! None of the conditions in the COND function evaluated to true.\n");
    }
}
