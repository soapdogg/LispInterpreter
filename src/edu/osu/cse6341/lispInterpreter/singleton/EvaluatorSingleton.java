package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import lombok.Getter;

@Getter
public enum EvaluatorSingleton {
    INSTANCE;

    private final NodeEvaluator nodeEvaluator;

    EvaluatorSingleton() {
        nodeEvaluator = NodeEvaluator.newInstance();
    }
}
