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
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator(),
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
        carFunction = CarFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator()
        );
        cdrFunction = CdrFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator()
        );
        condFunction = CondFunction.newInstance(
            AsserterSingleton.INSTANCE.getCondFunctionParameterAsserter(),
            EvaluatorSingleton.INSTANCE.getCondFunctionEvaluator()
        );
        consFunction = ConsFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );

        eqFunction = EqFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator(),
            ValueRetrieverSingleton.INSTANCE.getAtomicValueRetriever(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
        greaterFunction = GreaterFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator(),
            ValueRetrieverSingleton.INSTANCE.getNumericValueRetriever(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
        intFunction = IntFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator(),
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            ValueRetrieverSingleton.INSTANCE.getAtomicValueRetriever(),
            DeterminerSingleton.INSTANCE.getNumericStringDeterminer(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
        lessFunction = LessFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator(),
            ValueRetrieverSingleton.INSTANCE.getNumericValueRetriever(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
        minusFunction = MinusFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator(),
            ValueRetrieverSingleton.INSTANCE.getNumericValueRetriever(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
        nullFunction = NullFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator(),
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            ValueRetrieverSingleton.INSTANCE.getAtomicValueRetriever(),
            ComparatorSingleton.INSTANCE.getNodeValueComparator(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
        plusFunction = PlusFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator(),
            ValueRetrieverSingleton.INSTANCE.getNumericValueRetriever(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
        quoteFunction = QuoteFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever()
        );
        timesFunction = TimesFunction.newInstance(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator(),
            ValueRetrieverSingleton.INSTANCE.getNumericValueRetriever(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
    }
}
