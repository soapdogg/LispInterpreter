package edu.osu.cse6341.lispInterpreter.program.types;

public class AnyBoolType implements IType{

    @Override
    public String toString(){
        return "AnyBool";
    }

    @Override
    public boolean equals(Object o){
        return o instanceof AnyBoolType;
    }

    @Override
    public int hashCode(){
        return 1;
    }

    @Override
    public int getLength() {
        return 0;
    }
}
