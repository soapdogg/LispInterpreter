package edu.osu.cse6341.lispInterpreter.constants;

import java.util.regex.Pattern;

public class TokenValueConstants {
    public static final String CLOSE_PARENTHESES = ")";

    public static final Pattern ERROR_STATE_PATTERN = Pattern.compile("[0-9]+[A-Z]+[0-9A-Z]*");
}
