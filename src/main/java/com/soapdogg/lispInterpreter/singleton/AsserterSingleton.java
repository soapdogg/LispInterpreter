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
        functionLengthAsserter = new FunctionLengthAsserter(
            DeterminerSingleton.INSTANCE.getFunctionLengthDeterminer()
        );

        final Set<TokenKind> startingTokenKindSet = Set.of(
            TokenKind.OPEN_TOKEN,
            TokenKind.NUMERIC_TOKEN,
            TokenKind.LITERAL_TOKEN
        );
        tokenKindAsserter = new TokenKindAsserter(
            startingTokenKindSet
        );
        condFunctionParameterAsserter = new CondFunctionParameterAsserter(
            ComparatorSingleton.INSTANCE.getNodeValueComparator(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            functionLengthAsserter
        );
        userDefinedFormalParametersAsserter = new UserDefinedFormalParametersAsserter(
            InvalidUserDefinedNameConstants.InvalidNames
        );
        userDefinedFunctionNameAsserter = new UserDefinedFunctionNameAsserter(
            DeterminerSingleton.INSTANCE.getInvalidNameDeterminer()
        );
        atomRootNodeAsserter = new AtomRootNodeAsserter(
            DeterminerSingleton.INSTANCE.getNumericStringDeterminer(),
            ComparatorSingleton.INSTANCE.getNodeValueComparator()
        );
        lineFormatAsserter = new LineFormatAsserter(
            TokenValueConstants.ERROR_STATE_PATTERN
        );
    }
}
