package edu.osu.cse6341.lispInterpreter.program.types;

public class TrueType implements IType {

    @Override
    public String toString(){
        return "True";
    }

    @Override
    public boolean equals(Object o){
        return o instanceof TrueType;
    }

    @Override
    public int hashCode(){
        return 5;
    }

    @Override
    public int getLength(){
        return 0;
    }
}
