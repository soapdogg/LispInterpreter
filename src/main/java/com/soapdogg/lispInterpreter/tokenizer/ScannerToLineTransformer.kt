package com.soapdogg.lispInterpreter.tokenizer

import java.util.Scanner
import java.util.Spliterator
import java.util.Spliterators
import java.util.stream.StreamSupport
import kotlin.streams.toList

class ScannerToLineTransformer {
    fun transformScannerInputToLines(
        scanner: Scanner
    ): List<String> {
        return StreamSupport.stream(
            Spliterators.spliteratorUnknownSize(scanner, Spliterator.ORDERED),
            true
        ).toList()
    }
}