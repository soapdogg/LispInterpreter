package edu.osu.cse6341.lispInterpreter.program;

public interface IPrettyPrintable
{
    String getListNotationToString(boolean isFirst);

    String getDotNotationToString();

    String getTypeToString();
}
