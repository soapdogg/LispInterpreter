package edu.osu.cse6341.lispInterpreter.program.types;

public class AnyNatType implements IType{

    @Override
    public String toString(){
        return "AnyNat";
    }


    @Override
    public boolean equals(Object o){
        return o instanceof AnyNatType;
    }

    @Override
    public int hashCode(){
        return 2;
    }

    @Override
    public int getLength() {
        return 0;
    }
}
