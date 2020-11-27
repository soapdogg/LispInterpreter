package com.soapdogg.lispInterpreter.singleton;

import com.soapdogg.lispInterpreter.functions.*;
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
        atomFunction = new AtomFunction(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator(),
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
        carFunction = new CarFunction(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator()
        );
        cdrFunction = new CdrFunction(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator()
        );
        condFunction = new CondFunction(
            AsserterSingleton.INSTANCE.getCondFunctionParameterAsserter(),
            EvaluatorSingleton.INSTANCE.getCondFunctionEvaluator()
        );
        consFunction = new ConsFunction(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
        eqFunction = new EqFunction(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator(),
            ValueRetrieverSingleton.INSTANCE.getAtomicValueRetriever(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
        greaterFunction = new GreaterFunction(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator(),
            ValueRetrieverSingleton.INSTANCE.getNumericValueRetriever(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
        intFunction = new IntFunction(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator(),
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            ValueRetrieverSingleton.INSTANCE.getAtomicValueRetriever(),
            DeterminerSingleton.INSTANCE.getNumericStringDeterminer(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
        lessFunction = new LessFunction(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator(),
            ValueRetrieverSingleton.INSTANCE.getNumericValueRetriever(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
        minusFunction = new MinusFunction(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator(),
            ValueRetrieverSingleton.INSTANCE.getNumericValueRetriever(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
        nullFunction = new NullFunction(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator(),
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            ValueRetrieverSingleton.INSTANCE.getAtomicValueRetriever(),
            ComparatorSingleton.INSTANCE.getNodeValueComparator(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
        plusFunction = new PlusFunction(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator(),
            ValueRetrieverSingleton.INSTANCE.getNumericValueRetriever(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
        quoteFunction = new QuoteFunction(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever()
        );
        timesFunction = new TimesFunction(
            AsserterSingleton.INSTANCE.getFunctionLengthAsserter(),
            EvaluatorSingleton.INSTANCE.getNodeEvaluator(),
            ValueRetrieverSingleton.INSTANCE.getNumericValueRetriever(),
            ValueRetrieverSingleton.INSTANCE.getListValueRetriever(),
            GeneratorSingleton.INSTANCE.getNodeGenerator()
        );
    }
}
