package edu.osu.cse6341.lispInterpreter.comparator;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class NodeValueComparator {

    public boolean equalsNil(final String value){
        return ReservedValuesConstants.NIL.equals(value);
    }

    public boolean equalsT(final String value){
        return ReservedValuesConstants.T.equals(value);
    }
}
