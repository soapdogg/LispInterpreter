package edu.osu.cse6341.lispInterpreter.program.types;

public class AnyNatType implements IType{

    @Override
    public String toString(){
        return "AnyNat";
    }

    @Override
    public int getLength() {
        return 0;
    }
}
