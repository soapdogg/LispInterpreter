package edu.osu.cse6341.lispInterpreter.program.types;

public class AnyBoolType implements IType{

    @Override
    public String toString(){
        return "AnyBool";
    }

    @Override
    public int getLength() {
        return 0;
    }
}
