package com.soapdogg.lispInterpreter.functions;

import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.generator.NodeGenerator;
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever;
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever;
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants;
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants;
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator;
import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor()
public class TimesFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final NodeEvaluator nodeEvaluator;
    private final NumericValueRetriever numericValueRetriever;
    private final ListValueRetriever listValueRetriever;
    private final NodeGenerator nodeGenerator;

    @Override
    public Node evaluateLispFunction(
        final Node params,
        final List<UserDefinedFunction> userDefinedFunctions,
        final Map<String, ? extends Node> variableNameToValueMap
    ) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.TIMES,
            FunctionLengthConstants.THREE,
            params
        );

        Node evaluatedAddress = nodeEvaluator.evaluate(
            params,
            userDefinedFunctions,
            variableNameToValueMap,
            true
        );
        int leftValue = numericValueRetriever.retrieveNumericValue(
            evaluatedAddress,
            1,
            FunctionNameConstants.TIMES
        );
        ExpressionNode expressionNodeParams = listValueRetriever.retrieveListValue(
            params,
            FunctionNameConstants.TIMES,
            variableNameToValueMap
        );
        Node data = expressionNodeParams.getData();
        Node evaluatedData = nodeEvaluator.evaluate(
            data,
            userDefinedFunctions,
            variableNameToValueMap,
            true
        );
        int rightValue = numericValueRetriever.retrieveNumericValue(
            evaluatedData,
            2,
            FunctionNameConstants.TIMES
        );
        int result = leftValue * rightValue;
        return nodeGenerator.generateAtomNode(result);
    }
}
