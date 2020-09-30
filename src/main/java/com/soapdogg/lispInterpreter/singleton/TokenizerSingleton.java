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
        scannerToLineTransformer = ScannerToLineTransformer.newInstance();
        wordTokenizer = WordTokenizer.newInstance(
            GeneratorSingleton.INSTANCE.getTokenGenerator(),
            DeterminerSingleton.INSTANCE.getNumericTokenValueEndIndexDeterminer(),
            DeterminerSingleton.INSTANCE.getLiteralTokenValueEndIndexDeterminer()
        );
        lineTokenizer = LineTokenizer.newInstance(
            wordTokenizer
        );
        tokenizer = Tokenizer.newInstance(
            scannerToLineTransformer,
            AsserterSingleton.INSTANCE.getLineFormatAsserter(),
            lineTokenizer
        );
    }
}
