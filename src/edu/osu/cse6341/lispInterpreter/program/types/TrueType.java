package edu.osu.cse6341.lispInterpreter.program.types;

public class TrueType implements IType {

    @Override
    public String toString(){
        return "True";
    }

    @Override
    public int getLength(){
        return 0;
    }
}
