package com.soapdogg.lispInterpreter.determiner

class NumericTokenValueEndIndexDeterminer {
    fun determineEndIndexOfNumericTokenValue(
        word: String,
        startingPos: Int
    ): Int {
        val wordLength = word.length
        val firstNonAlphaNumericIndex = IntRange(startingPos, wordLength - 1)
            .firstOrNull { i: Int -> !Character.isDigit(word[i]) }
        val endIndex = firstNonAlphaNumericIndex ?: Integer.MAX_VALUE
        return wordLength.coerceAtMost(endIndex)
    }
}