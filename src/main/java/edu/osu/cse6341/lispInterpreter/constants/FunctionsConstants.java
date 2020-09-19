package edu.osu.cse6341.lispInterpreter.constants;

import edu.osu.cse6341.lispInterpreter.functions.LispFunction;
import edu.osu.cse6341.lispInterpreter.singleton.FunctionSingleton;

import java.util.HashMap;
import java.util.Map;

public class FunctionsConstants {

    public static final Map<String, LispFunction> functionMap;

    static{
        functionMap = new HashMap<>();
        functionMap.put(FunctionNameConstants.ATOM, FunctionSingleton.INSTANCE.getAtomFunction());
        functionMap.put(FunctionNameConstants.CAR, FunctionSingleton.INSTANCE.getCarFunction());
        functionMap.put(FunctionNameConstants.CDR, FunctionSingleton.INSTANCE.getCdrFunction());
        functionMap.put(FunctionNameConstants.COND, FunctionSingleton.INSTANCE.getCondFunction());
        functionMap.put(FunctionNameConstants.CONS, FunctionSingleton.INSTANCE.getConsFunction());
        functionMap.put(FunctionNameConstants.EQ, FunctionSingleton.INSTANCE.getEqFunction());
        functionMap.put(FunctionNameConstants.GREATER, FunctionSingleton.INSTANCE.getGreaterFunction());
        functionMap.put(FunctionNameConstants.INT, FunctionSingleton.INSTANCE.getIntFunction());
        functionMap.put(FunctionNameConstants.LESS, FunctionSingleton.INSTANCE.getLessFunction());
        functionMap.put(FunctionNameConstants.MINUS, FunctionSingleton.INSTANCE.getMinusFunction());
        functionMap.put(FunctionNameConstants.NULL, FunctionSingleton.INSTANCE.getNullFunction());
        functionMap.put(FunctionNameConstants.PLUS, FunctionSingleton.INSTANCE.getPlusFunction());
        functionMap.put(FunctionNameConstants.QUOTE, FunctionSingleton.INSTANCE.getQuoteFunction());
        functionMap.put(FunctionNameConstants.TIMES, FunctionSingleton.INSTANCE.getTimesFunction());
    }
}
