package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.singleton.ComparatorSingleton;
import edu.osu.cse6341.lispInterpreter.singleton.DeterminerSingleton;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class ExpressionNode implements LispNode {

	private final LispNode address;
	private final LispNode data;
	private final boolean isList;

    @Override
    public String getListNotationToString(boolean isFirst){
        StringBuilder sb = new StringBuilder();
        if(isFirst) sb.append('(');
        sb.append(address.getListNotationToString(address.isNodeList()));
        sb.append(getDataListNotationAsString());
        return sb.toString();
    }

	public LispNode getData(){
		return data;
	}

	public LispNode getAddress(){
	    return address;
	}

    private String getDataListNotationAsString(){
        if(!data.isNodeList()) {
            String dataString = (DeterminerSingleton.INSTANCE.getNumericStringDeterminer().isStringNumeric(data.getNodeValue()) || ComparatorSingleton.INSTANCE.getNodeValueComparator().equalsT(data.getNodeValue()))
                    ? (" . " + data.getListNotationToString(false))
                    : "";
            return dataString + ')';
        }
        else return ' ' + data.getListNotationToString(false);
    }

	@Override
	public String getNodeValue() {
		return address == null
			? ReservedValuesConstants.NIL
			: address.getNodeValue() + ' ' + data.getNodeValue();
	}

	@Override
	public boolean isNodeList() {
		return isList;
	}

	@Override
	public int parameterLength() {
		return isNodeList() ? data.parameterLength() + 1 : 0;
	}
}
