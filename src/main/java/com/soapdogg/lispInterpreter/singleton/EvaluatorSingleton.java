package com.soapdogg.lispInterpreter.singleton;

import com.soapdogg.lispInterpreter.evaluator.AtomNodeEvaluator;
import com.soapdogg.lispInterpreter.evaluator.CondFunctionEvaluator;
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator;
import com.soapdogg.lispInterpreter.evaluator.ProgramEvaluator;
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
