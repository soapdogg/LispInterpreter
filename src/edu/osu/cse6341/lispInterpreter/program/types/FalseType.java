package edu.osu.cse6341.lispInterpreter.program.types;

public class FalseType implements IType{

    @Override
    public String toString(){
        return "False";
    }

    @Override
    public boolean equals(Object o){
        return o instanceof FalseType;
    }

    @Override
    public int hashCode(){
        return 3;
    }

    @Override
    public int getLength() {
        return 0;
    }
}
