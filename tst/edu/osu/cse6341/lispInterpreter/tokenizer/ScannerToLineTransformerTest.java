package edu.osu.cse6341.lispInterpreter.tokenizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

class ScannerToLineTransformerTest {

    private Scanner in;

    private ScannerToLineTransformer scannerToLineTransformer;

    @BeforeEach
    void setup() throws IOException {
        in = new Scanner(Paths.get("data/input/project3/atom/valid.lisp"));

        scannerToLineTransformer = ScannerToLineTransformer.newInstance();
    }

    @Test
    void transformScannerInputToLinesTest() {
        List<String> actual = scannerToLineTransformer.transformScannerInputToLines(
            in
        );

        Assertions.assertFalse(actual.isEmpty());
    }
}
