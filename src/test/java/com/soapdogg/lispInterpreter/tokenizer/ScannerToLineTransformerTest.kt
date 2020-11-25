package com.soapdogg.lispInterpreter.tokenizer

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.nio.file.Paths
import java.util.*

class ScannerToLineTransformerTest {

    private var scanner: Scanner = Scanner(Paths.get("data/input/project3/atom/valid.lisp"))
    private var scannerToLineTransformer: ScannerToLineTransformer = ScannerToLineTransformer()

    @Test
    fun transformScannerInputToLinesTest() {
        val actual = scannerToLineTransformer.transformScannerInputToLines(
            scanner
        )
        Assertions.assertFalse(actual.isEmpty())
    }
}