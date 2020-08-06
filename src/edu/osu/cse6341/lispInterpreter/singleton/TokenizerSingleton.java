package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import lombok.Getter;

@Getter
public enum TokenizerSingleton {
    INSTANCE;

    private final Tokenizer tokenizer;

    TokenizerSingleton() {
        tokenizer = Tokenizer.newInstance();
    }
}
