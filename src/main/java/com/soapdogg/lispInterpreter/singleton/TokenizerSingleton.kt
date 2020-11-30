package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.tokenizer.LineTokenizer
import com.soapdogg.lispInterpreter.tokenizer.ScannerToLineTransformer
import com.soapdogg.lispInterpreter.tokenizer.Tokenizer
import com.soapdogg.lispInterpreter.tokenizer.WordTokenizer

enum class TokenizerSingleton {
    INSTANCE;

    val scannerToLineTransformer: ScannerToLineTransformer = ScannerToLineTransformer()
    val wordTokenizer: WordTokenizer = WordTokenizer(
        GeneratorSingleton.INSTANCE.tokenGenerator,
        DeterminerSingleton.INSTANCE.numericTokenValueEndIndexDeterminer,
        DeterminerSingleton.INSTANCE.literalTokenValueEndIndexDeterminer
    )
    val lineTokenizer: LineTokenizer
    val tokenizer: Tokenizer

    init {
        lineTokenizer = LineTokenizer(
            wordTokenizer
        )
        tokenizer = Tokenizer(
            scannerToLineTransformer,
            AsserterSingleton.INSTANCE.lineFormatAsserter,
            lineTokenizer
        )
    }
}