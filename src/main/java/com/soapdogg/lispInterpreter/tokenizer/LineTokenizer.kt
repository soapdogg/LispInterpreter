package com.soapdogg.lispInterpreter.tokenizer

import com.soapdogg.lispInterpreter.constants.TokenValueConstants
import com.soapdogg.lispInterpreter.datamodels.Token

class LineTokenizer (
    private val wordTokenizer: WordTokenizer
){

    fun tokenizeLine(
        line: String
    ): List<Token> {
        val trimmedLine = line.trim { it <= ' ' }
        val words = trimmedLine.split(TokenValueConstants.WHITE_SPACE_PATTERN.toRegex())
        return words.map {
            word -> wordTokenizer.tokenizeWord(word)
        }.flatMap { it.toList() }
    }
}