package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.program.Environment;
import lombok.Getter;

import java.util.HashMap;

@Getter
public enum EnvironmentSingleton {
    INSTANCE;

    private final Environment environment;

    EnvironmentSingleton() {
        environment = Environment.newInstance(
            new HashMap<>()
        );
    }
}
