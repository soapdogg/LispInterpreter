package com.soapdogg.lispInterpreter.constants;

import java.util.regex.Pattern;

public class TokenValueConstants {
    public static final char OPEN_PARENTHESES = '(';
    public static final char CLOSE_PARENTHESES = ')';

    public static final Pattern ERROR_STATE_PATTERN = Pattern.compile("[0-9]+[A-Z]+[0-9A-Z]*");
    public static final String WHITE_SPACE_PATTERN = "\\s+";
}
