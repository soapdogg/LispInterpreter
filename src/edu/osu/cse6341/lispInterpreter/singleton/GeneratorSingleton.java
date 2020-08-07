package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.functions.DefunFunction;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.generator.UserDefinedFunctionGenerator;
import lombok.Getter;

@Getter
public enum GeneratorSingleton {
    INSTANCE;

    private final NodeGenerator nodeGenerator;
    private final UserDefinedFunctionGenerator userDefinedFunctionGenerator;

    GeneratorSingleton() {
        nodeGenerator = NodeGenerator.newInstance();
        DefunFunction defunFunction = DefunFunction.newInstance(
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ValueRetrieverSingleton.INSTANCE.getAtomicValueRetriever(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            AsserterSingleton.INSTANCE.getUserDefinedFunctionNameAsserter(),
            AsserterSingleton.INSTANCE.getUserDefinedFormalParametersAsserter()
        );
        userDefinedFunctionGenerator =UserDefinedFunctionGenerator.newInstance(
            defunFunction
        );
    }
}
