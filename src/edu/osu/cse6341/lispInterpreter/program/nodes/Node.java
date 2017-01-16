package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;
import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.program.IPrettyPrintable;

public abstract class Node implements IParsable, IEvaluatable, IPrettyPrintable{

    protected String errorMessage;
    protected boolean hasError;

    public abstract Node newInstance();
    public abstract boolean isList();
    public abstract boolean isNumeric();
    public abstract boolean isLiteral();

    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public void markErrorPresent(){
        hasError = true;
    }

    public boolean hasError(){
        return hasError;
    }


}
