package edu.osu.cse6341.lispInterpreter.program.types;

public class ListType implements IType {

    private int length;

    public ListType(int length){
        this.length = length;
    }

    @Override
    public String toString(){
        return "List[>=" + length + "]";
    }

    @Override
    public boolean equals(Object o){
        return o instanceof ListType;
    }

    @Override
    public int hashCode(){
        return 4;
    }

    @Override
    public int getLength() {
        return length;
    }
}
