package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.exceptions.InvalidTokenException

import java.util.regex.Pattern

class LineFormatAsserter(
    private val errorStatePattern: Pattern
) {

    fun assertLineFormat(line: String) {
        val matcher = errorStatePattern.matcher(line)
        val hasErrorState = matcher.find()
        if (hasErrorState) {
            val matchResult = matcher.toMatchResult()
            val start = matchResult.start()
            val end = matchResult.end()
            val errorMessage = """
                Error! Invalid token: ${line.substring(start, end)}
                
                """.trimIndent()
            throw InvalidTokenException(errorMessage)
        }
    }
}