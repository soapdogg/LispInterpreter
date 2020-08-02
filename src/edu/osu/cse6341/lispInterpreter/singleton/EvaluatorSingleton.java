package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.evaluator.CondFunctionEvaluator;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.functions.CondFunction;
import lombok.Getter;

@Getter
public enum EvaluatorSingleton {
    INSTANCE;

    private final NodeEvaluator nodeEvaluator;
    private final CondFunctionEvaluator condFunctionEvaluator;

    EvaluatorSingleton() {
        nodeEvaluator = NodeEvaluator.newInstance(
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer()
        );
        condFunctionEvaluator = CondFunctionEvaluator.newInstance(
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            nodeEvaluator,
            ComparatorSingleton.INSTANCE.getNodeValueComparator()
        );
    }
}
