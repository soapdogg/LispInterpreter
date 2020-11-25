package com.soapdogg.lispInterpreter.constants

import java.util.regex.Pattern

object TokenValueConstants {
    const val OPEN_PARENTHESES = '('
    const val CLOSE_PARENTHESES = ')'
    @JvmField
    val ERROR_STATE_PATTERN = Pattern.compile("[0-9]+[A-Z]+[0-9A-Z]*")
    const val WHITE_SPACE_PATTERN = "\\s+"
}