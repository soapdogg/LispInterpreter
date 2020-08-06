package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.program.Program;
import lombok.Getter;

@Getter
public enum ProgramSingleton {
    INSTANCE;

    private final Program program;

    ProgramSingleton() {
        program = Program.newInstance();
    }
}
