package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.tokenizer.LineTokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.ScannerToLineTransformer;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import lombok.Getter;

@Getter
public enum TokenizerSingleton {
    INSTANCE;

    private final ScannerToLineTransformer scannerToLineTransformer;
    private final LineTokenizer lineTokenizer;
    private final Tokenizer tokenizer;

    TokenizerSingleton() {
        scannerToLineTransformer = ScannerToLineTransformer.newInstance();
        lineTokenizer = LineTokenizer.newInstance();
        tokenizer = Tokenizer.newInstance(
            scannerToLineTransformer,
            AsserterSingleton.INSTANCE.getLineFormatAsserter(),
            lineTokenizer
        );
    }
}
