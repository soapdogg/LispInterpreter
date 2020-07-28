package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.functions.*;
import lombok.Getter;

@Getter
public enum  FunctionSingleton {
    INSTANCE;

    private final AtomFunction atomFunction;
    private final CarFunction carFunction;
    private final CdrFunction cdrFunction;
    private final CondFunction condFunction;
    private final ConsFunction consFunction;
    private final DefunFunction defunFunction;
    private final EqFunction eqFunction;
    private final GreaterFunction greaterFunction;
    private final IntFunction intFunction;
    private final LessFunction lessFunction;
    private final MinusFunction minusFunction;
    private final NullFunction nullFunction;
    private final PlusFunction plusFunction;
    private final QuoteFunction quoteFunction;
    private final TimesFunction timesFunction;


    FunctionSingleton() {
        atomFunction = AtomFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter()
        );
        carFunction = CarFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever()
        );
        cdrFunction = CdrFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever()
        );
        condFunction = CondFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            ComparatorSingleton.INSTANCE.getNodeValueComparator()
        );
        consFunction = ConsFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter()
        );
        defunFunction = DefunFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ValueRetrieverSingleton.INSTANCE.getAtomicValueRetriever(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever()
        );
        eqFunction = EqFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ValueRetrieverSingleton.INSTANCE.getAtomicValueRetriever()
        );
        greaterFunction = GreaterFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ValueRetrieverSingleton.INSTANCE.getNumericValueRetriever()
        );
        intFunction = IntFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter()
        );
        lessFunction = LessFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ValueRetrieverSingleton.INSTANCE.getNumericValueRetriever()
        );
        minusFunction = MinusFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ValueRetrieverSingleton.INSTANCE.getNumericValueRetriever()
        );
        nullFunction = NullFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ComparatorSingleton.INSTANCE.getNodeValueComparator()
        );
        plusFunction = PlusFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ValueRetrieverSingleton.INSTANCE.getNumericValueRetriever()
        );
        quoteFunction = QuoteFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter()
        );
        timesFunction = TimesFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ValueRetrieverSingleton.INSTANCE.getNumericValueRetriever()
        );
    }
}
