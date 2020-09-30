package com.soapdogg.lispInterpreter.singleton;

import com.soapdogg.lispInterpreter.asserter.*;
import com.soapdogg.lispInterpreter.constants.InvalidUserDefinedNameConstants;
import com.soapdogg.lispInterpreter.constants.TokenValueConstants;
import com.soapdogg.lispInterpreter.datamodels.TokenKind;
import lombok.Getter;

import java.util.Set;

@Getter
public enum AsserterSingleton {
    INSTANCE;

    private final FunctionLengthAsserter functionLengthAsserter;
    private final TokenKindAsserter tokenKindAsserter;
    private final CondFunctionParameterAsserter condFunctionParameterAsserter;
    private final UserDefinedFormalParametersAsserter userDefinedFormalParametersAsserter;
    private final UserDefinedFunctionNameAsserter userDefinedFunctionNameAsserter;
    private final AtomRootNodeAsserter atomRootNodeAsserter;
    private final LineFormatAsserter lineFormatAsserter;

    AsserterSingleton() {
        functionLengthAsserter = FunctionLengthAsserter.newInstance(
            DeterminerSingleton.INSTANCE.getFunctionLengthDeterminer()
        );

        final Set<TokenKind> startingTokenKindSet = Set.of(
            TokenKind.OPEN_TOKEN,
            TokenKind.NUMERIC_TOKEN,
            TokenKind.LITERAL_TOKEN
        );
        tokenKindAsserter = TokenKindAsserter.newInstance(
            startingTokenKindSet
        );
        condFunctionParameterAsserter = CondFunctionParameterAsserter.newInstance(
            ComparatorSingleton.INSTANCE.getNodeValueComparator(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            functionLengthAsserter
        );
        userDefinedFormalParametersAsserter = UserDefinedFormalParametersAsserter.newInstance(
            InvalidUserDefinedNameConstants.InvalidNames
        );
        userDefinedFunctionNameAsserter = UserDefinedFunctionNameAsserter.newInstance(
            DeterminerSingleton.INSTANCE.getInvalidNameDeterminer()
        );
        atomRootNodeAsserter = AtomRootNodeAsserter.newInstance(
            DeterminerSingleton.INSTANCE.getNumericStringDeterminer(),
            ComparatorSingleton.INSTANCE.getNodeValueComparator()
        );
        lineFormatAsserter = LineFormatAsserter.newInstance(
            TokenValueConstants.ERROR_STATE_PATTERN
        );
    }
}
