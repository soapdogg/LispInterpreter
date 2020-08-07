package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.evaluator.CondFunctionEvaluator;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.evaluator.ProgramEvaluator;
import lombok.Getter;

@Getter
public enum EvaluatorSingleton {
    INSTANCE;

    private final NodeEvaluator nodeEvaluator;
    private final CondFunctionEvaluator condFunctionEvaluator;
    private final ProgramEvaluator programEvaluator;

    EvaluatorSingleton() {
        nodeEvaluator = NodeEvaluator.newInstance(
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            DeterminerSingleton.INSTANCE.getUserDefinedFunctionNameDeterminer(),
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter()
        );
        condFunctionEvaluator = CondFunctionEvaluator.newInstance(
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            nodeEvaluator,
            ComparatorSingleton.INSTANCE.getNodeValueComparator()
        );
        programEvaluator = ProgramEvaluator.newInstance(
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            DeterminerSingleton.INSTANCE.getNumericStringDeterminer(),
            ComparatorSingleton.INSTANCE.getNodeValueComparator(),
            nodeEvaluator
        );
    }
}
