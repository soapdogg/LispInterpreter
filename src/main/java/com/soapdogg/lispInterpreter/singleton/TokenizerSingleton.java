package com.soapdogg.lispInterpreter.singleton;

import com.soapdogg.lispInterpreter.tokenizer.LineTokenizer;
import com.soapdogg.lispInterpreter.tokenizer.ScannerToLineTransformer;
import com.soapdogg.lispInterpreter.tokenizer.Tokenizer;
import com.soapdogg.lispInterpreter.tokenizer.WordTokenizer;
import lombok.Getter;

@Getter
public enum TokenizerSingleton {
    INSTANCE;

    private final ScannerToLineTransformer scannerToLineTransformer;
    private final WordTokenizer wordTokenizer;
    private final LineTokenizer lineTokenizer;
    private final Tokenizer tokenizer;

    TokenizerSingleton() {
    scannerToLineTransformer = new ScannerToLineTransformer();
        wordTokenizer = WordTokenizer.newInstance(
            GeneratorSingleton.INSTANCE.getTokenGenerator(),
            DeterminerSingleton.INSTANCE.getNumericTokenValueEndIndexDeterminer(),
            DeterminerSingleton.INSTANCE.getLiteralTokenValueEndIndexDeterminer()
        );
        lineTokenizer = new LineTokenizer(
            wordTokenizer
        );
        tokenizer = new Tokenizer(
            scannerToLineTransformer,
            AsserterSingleton.INSTANCE.getLineFormatAsserter(),
            lineTokenizer
        );
    }
}
