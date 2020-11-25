package com.soapdogg.lispInterpreter.tokenizer

import com.soapdogg.lispInterpreter.asserter.LineFormatAsserter
import com.soapdogg.lispInterpreter.datamodels.Token
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class TokenizerTest {
    private var scanner: Scanner = Scanner(System.`in`)
    private var scannerToLineTransformer: ScannerToLineTransformer = Mockito.mock(ScannerToLineTransformer::class.java)
    private var lineFormatAsserter: LineFormatAsserter = Mockito.mock(LineFormatAsserter::class.java)
    private var lineTokenizer: LineTokenizer = Mockito.mock(LineTokenizer::class.java)
    private var tokenizer: Tokenizer = Tokenizer(
        scannerToLineTransformer,
        lineFormatAsserter,
        lineTokenizer
    )


    @Test
    fun tokenizerTest() {
        val line = "line"
        val lines = listOf(line)
        Mockito.`when`(
            scannerToLineTransformer.transformScannerInputToLines(scanner)
        ).thenReturn(lines)

        val token = Mockito.mock(Token::class.java)
        val tokens = listOf(token)
        Mockito.`when`(
            lineTokenizer.tokenizeLine(line)
        ).thenReturn(tokens)

        val actual = tokenizer.tokenize(scanner)

        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(token, actual[0])
        Mockito.verify(lineFormatAsserter).assertLineFormat(line)
    }
}