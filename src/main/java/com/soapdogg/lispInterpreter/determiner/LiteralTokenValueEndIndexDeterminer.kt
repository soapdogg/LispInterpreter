package com.soapdogg.lispInterpreter.determiner

class LiteralTokenValueEndIndexDeterminer {
    fun determineEndIndexOfLiteralTokenValue(
        word: String,
        startingPos: Int
    ): Int {
        val wordLength = word.length
        val firstNonAlphaNumericIndex = IntRange(startingPos, wordLength - 1)
            .firstOrNull { i: Int -> !Character.isAlphabetic(word[i].toInt()) && !Character.isDigit(word[i]) }
        val endIndex = firstNonAlphaNumericIndex ?: Integer.MAX_VALUE
        return wordLength.coerceAtMost(endIndex)
    }
}