package edu.osu.cse6341.lispInterpreter.program.types;

public class FalseType implements IType{

    @Override
    public String toString(){
        return "False";
    }

    @Override
    public int getLength() {
        return 0;
    }
}
