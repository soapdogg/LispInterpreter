package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import lombok.Getter;

@Getter
public enum GeneratorSingleton {
    INSTANCE;

    private final NodeGenerator nodeGenerator;

    GeneratorSingleton() {
        nodeGenerator = NodeGenerator.newInstance();
    }
}
