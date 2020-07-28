package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;
import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.program.IPrettyPrintable;

public abstract class Node implements IParsable, IEvaluatable, IPrettyPrintable{

    public abstract Node newInstance();
    public abstract String getValue();
    public abstract boolean isList();
    public abstract boolean isNumeric();
    public abstract int getLength();

}
