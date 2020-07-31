package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "newInstance")
public class AtomNode implements LispNode {

	private final String value;

    @Override
    public String getNodeValue() {
        return value;
    }

    @Override
    public int parameterLength() {
        return value.equals(ReservedValuesConstants.NIL) ? 0: 1;
    }
}
