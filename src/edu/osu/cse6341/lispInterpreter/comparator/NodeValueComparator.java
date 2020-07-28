package edu.osu.cse6341.lispInterpreter.comparator;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class NodeValueComparator {

    public boolean equalsNil(String value){
        return ReservedValuesConstants.NIL.equals(value);
    }

    public boolean equalsT(String value){
        return ReservedValuesConstants.T.equals(value);
    }
}
