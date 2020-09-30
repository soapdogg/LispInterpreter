package com.soapdogg.lispInterpreter.functions;

import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer;
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer;
import com.soapdogg.lispInterpreter.generator.NodeGenerator;
import com.soapdogg.lispInterpreter.valueretriver.AtomicValueRetriever;
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants;
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants;
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator;
import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor(staticName = "newInstance")
public class IntFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final NodeEvaluator nodeEvaluator;
    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final AtomicValueRetriever atomicValueRetriever;
    private final NumericStringDeterminer numericStringDeterminer;
    private final NodeGenerator nodeGenerator;

    @Override
    public Node evaluateLispFunction(
        final Node params,
        final List<UserDefinedFunction> userDefinedFunctions,
        final Map<String, Node> variableNameToValueMap
    ) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.INT,
            FunctionLengthConstants.TWO,
            params
        );
        Node evaluatedResult = nodeEvaluator.evaluate(
            params,
            userDefinedFunctions,
            variableNameToValueMap,
            true
        );
        if (expressionNodeDeterminer.isExpressionNode(evaluatedResult)) {
            return nodeGenerator.generateAtomNode(false);
        }
        String value = atomicValueRetriever.retrieveAtomicValue(
            evaluatedResult,
            1,
            FunctionNameConstants.INT
        );
        boolean result = numericStringDeterminer.isStringNumeric(value);
        return nodeGenerator.generateAtomNode(result);
    }
}
