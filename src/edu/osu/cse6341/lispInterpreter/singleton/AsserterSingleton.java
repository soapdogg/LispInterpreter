package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.asserter.*;
import edu.osu.cse6341.lispInterpreter.constants.InvalidUserDefinedNameConstants;
import edu.osu.cse6341.lispInterpreter.determiner.InvalidNameDeterminer;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.tokens.TokenKind;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public enum AsserterSingleton {
    INSTANCE;

    private final FunctionLengthAsserter functionLengthAsserter;
    private final TokenKindAsserter tokenKindAsserter;
    private final CondFunctionParameterAsserter condFunctionParameterAsserter;
    private final UserDefinedFormalParametersAsserter userDefinedFormalParametersAsserter;
    private final UserDefinedFunctionNameAsserter userDefinedFunctionNameAsserter;

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
    }
}
