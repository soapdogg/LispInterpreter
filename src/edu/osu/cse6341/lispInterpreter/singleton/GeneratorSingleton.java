package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.functions.DefunFunction;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.generator.TokenGenerator;
import edu.osu.cse6341.lispInterpreter.generator.UserDefinedFunctionFormalParameterGenerator;
import edu.osu.cse6341.lispInterpreter.generator.UserDefinedFunctionGenerator;
import lombok.Getter;

@Getter
public enum GeneratorSingleton {
    INSTANCE;

    private final NodeGenerator nodeGenerator;
    private final UserDefinedFunctionGenerator userDefinedFunctionGenerator;
    private final UserDefinedFunctionFormalParameterGenerator userDefinedFunctionFormalParameterGenerator;
    private final TokenGenerator tokenGenerator;

    GeneratorSingleton() {
        nodeGenerator = NodeGenerator.newInstance();
        userDefinedFunctionFormalParameterGenerator = UserDefinedFunctionFormalParameterGenerator.newInstance(
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            ValueRetrieverSingleton.INSTANCE.getAtomicValueRetriever()
        );
        DefunFunction defunFunction = DefunFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ValueRetrieverSingleton.INSTANCE.getAtomicValueRetriever(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            AsserterSingleton.INSTANCE.getUserDefinedFunctionNameAsserter(),
            userDefinedFunctionFormalParameterGenerator,
            AsserterSingleton.INSTANCE.getUserDefinedFormalParametersAsserter()
        );
        userDefinedFunctionGenerator = UserDefinedFunctionGenerator.newInstance(
            defunFunction
        );
        tokenGenerator = TokenGenerator.newInstance();
    }
}
