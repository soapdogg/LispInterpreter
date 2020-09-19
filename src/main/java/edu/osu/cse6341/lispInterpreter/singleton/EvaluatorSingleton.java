package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.evaluator.AtomNodeEvaluator;
import edu.osu.cse6341.lispInterpreter.evaluator.CondFunctionEvaluator;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.evaluator.ProgramEvaluator;
import lombok.Getter;

@Getter
public enum EvaluatorSingleton {
    INSTANCE;

    private final AtomNodeEvaluator atomNodeEvaluator;
    private final NodeEvaluator nodeEvaluator;
    private final CondFunctionEvaluator condFunctionEvaluator;
    private final ProgramEvaluator programEvaluator;

    EvaluatorSingleton() {
        atomNodeEvaluator = AtomNodeEvaluator.newInstance();
        nodeEvaluator = NodeEvaluator.newInstance(
            atomNodeEvaluator,
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
            AsserterSingleton.INSTANCE.getAtomRootNodeAsserter(),
            nodeEvaluator
        );
    }
}
