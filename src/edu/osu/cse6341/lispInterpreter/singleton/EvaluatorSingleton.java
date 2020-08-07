package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.evaluator.CondFunctionEvaluator;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import lombok.Getter;

@Getter
public enum EvaluatorSingleton {
    INSTANCE;

    private final NodeEvaluator nodeEvaluator;
    private final CondFunctionEvaluator condFunctionEvaluator;

    EvaluatorSingleton() {
        nodeEvaluator = NodeEvaluator.newInstance(
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            EnvironmentSingleton.INSTANCE.getEnvironment(),
            DeterminerSingleton.INSTANCE.getUserDefinedFunctionNameDeterminer(),
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter()
        );
        condFunctionEvaluator = CondFunctionEvaluator.newInstance(
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            nodeEvaluator,
            ComparatorSingleton.INSTANCE.getNodeValueComparator()
        );
    }
}
