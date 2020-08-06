package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.evaluator.CondFunctionEvaluator;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.evaluator.UserDefinedFunctionEvaluator;
import edu.osu.cse6341.lispInterpreter.functions.CondFunction;
import lombok.Getter;

@Getter
public enum EvaluatorSingleton {
    INSTANCE;

    private final NodeEvaluator nodeEvaluator;
    private final CondFunctionEvaluator condFunctionEvaluator;
    private final UserDefinedFunctionEvaluator userDefinedFunctionEvaluator;

    EvaluatorSingleton() {
        nodeEvaluator = NodeEvaluator.newInstance(
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            EnvironmentSingleton.INSTANCE.getEnvironment()
        );
        condFunctionEvaluator = CondFunctionEvaluator.newInstance(
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            nodeEvaluator,
            ComparatorSingleton.INSTANCE.getNodeValueComparator()
        );
        userDefinedFunctionEvaluator = UserDefinedFunctionEvaluator.newInstance(
            EnvironmentSingleton.INSTANCE.getEnvironment(),
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            nodeEvaluator
        );
    }
}
