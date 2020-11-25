package com.soapdogg.lispInterpreter.tokenizer

import com.soapdogg.lispInterpreter.asserter.LineFormatAsserter
import com.soapdogg.lispInterpreter.datamodels.Token
import java.util.Scanner

class Tokenizer(
    private val scannerToLineTransformer: ScannerToLineTransformer,
    private val lineFormatAsserter: LineFormatAsserter,
    private val lineTokenizer: LineTokenizer
) {

    fun tokenize(scanner: Scanner): List<Token> {
        val lines = scannerToLineTransformer.transformScannerInputToLines(
            scanner
        )
        lines.forEach { lineFormatAsserter.assertLineFormat(it) }
        return lines.map{
            line -> lineTokenizer.tokenizeLine(line)
        }.flatMap { it.toList() }
    }
}