package com.soapdogg.lispInterpreter.functions;

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer;
import com.soapdogg.lispInterpreter.generator.NodeGenerator;
import com.soapdogg.lispInterpreter.valueretriver.AtomicValueRetriever;
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants;
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants;
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator;
import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor()
public class NullFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final NodeEvaluator nodeEvaluator;
    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final AtomicValueRetriever atomicValueRetriever;
    private final NodeValueComparator nodeValueComparator;
    private final NodeGenerator nodeGenerator;

    @Override
    public Node evaluateLispFunction(
        final Node params,
        final List<UserDefinedFunction> userDefinedFunctions,
        final Map<String, ? extends Node> variableNameToValueMap
    ) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.NULL,
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
            FunctionNameConstants.NULL
        );
        boolean result = nodeValueComparator.equalsNil(value);
        return nodeGenerator.generateAtomNode(result);
    }
}
