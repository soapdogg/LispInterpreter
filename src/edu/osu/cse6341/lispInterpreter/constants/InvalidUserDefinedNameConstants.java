package edu.osu.cse6341.lispInterpreter.constants;

import java.util.HashSet;
import java.util.Set;

public class InvalidUserDefinedNameConstants {

    public static final Set<String> InvalidNames;

        static {
            InvalidNames = new HashSet<>();
            InvalidNames.add(FunctionNameConstants.ATOM);
            InvalidNames.add(FunctionNameConstants.CAR);
            InvalidNames.add(FunctionNameConstants.CDR);
            InvalidNames.add(FunctionNameConstants.COND);
            InvalidNames.add(FunctionNameConstants.CONS);
            InvalidNames.add(FunctionNameConstants.DEFUN);
            InvalidNames.add(FunctionNameConstants.EQ);
            InvalidNames.add(FunctionNameConstants.GREATER);
            InvalidNames.add(FunctionNameConstants.INT);
            InvalidNames.add(FunctionNameConstants.LESS);
            InvalidNames.add(FunctionNameConstants.MINUS);
            InvalidNames.add(FunctionNameConstants.NULL);
            InvalidNames.add(FunctionNameConstants.PLUS);
            InvalidNames.add(FunctionNameConstants.QUOTE);
            InvalidNames.add(FunctionNameConstants.TIMES);
            InvalidNames.add(ReservedValuesConstants.T);
            InvalidNames.add(ReservedValuesConstants.NIL);
        }
}
