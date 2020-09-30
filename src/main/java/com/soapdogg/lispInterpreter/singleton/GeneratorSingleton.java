package com.soapdogg.lispInterpreter.singleton;

import com.soapdogg.lispInterpreter.functions.DefunFunction;
import com.soapdogg.lispInterpreter.generator.NodeGenerator;
import com.soapdogg.lispInterpreter.generator.TokenGenerator;
import com.soapdogg.lispInterpreter.generator.UserDefinedFunctionFormalParameterGenerator;
import com.soapdogg.lispInterpreter.generator.UserDefinedFunctionGenerator;
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
