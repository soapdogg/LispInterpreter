package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.singleton.DeterminerSingleton;
import edu.osu.cse6341.lispInterpreter.singleton.EvaluatorSingleton;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor(staticName = "newInstance")
public class Program {

	public List<LispNode> evaluate(List<LispNode> rootNodes) throws Exception{
		List<LispNode> evaluatedNodes = new ArrayList<>();
		for(LispNode node: rootNodes) {
			boolean isNotList = !DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer().isExpressionNode(node);
			if (isNotList) {
				AtomNode atomNode = (AtomNode)node;
				boolean isNotNumeric = !DeterminerSingleton.INSTANCE.getNumericStringDeterminer().isStringNumeric(atomNode.getValue());
				boolean isNotT = !atomNode.getValue().equals(ReservedValuesConstants.T);
				boolean isNotNil = !atomNode.getValue().equals(ReservedValuesConstants.NIL);
				if (isNotNumeric && isNotT && isNotNil)
					throw new Exception("Error! " + atomNode.getValue() + " is not a valid atomic value!\n");
			}
			LispNode evaluatedNode = EvaluatorSingleton.INSTANCE.getNodeEvaluator().evaluate(
				node,
				false
			);
			if(evaluatedNode != null) evaluatedNodes.add(evaluatedNode);
		}
		return evaluatedNodes;
	}
}
