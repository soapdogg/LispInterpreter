package com.soapdogg.lispInterpreter.evaluator;

import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer;
import com.soapdogg.lispInterpreter.asserter.AtomRootNodeAsserter;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor(staticName = "newInstance")
public class ProgramEvaluator {

	private final ExpressionNodeDeterminer expressionNodeDeterminer;
	private final AtomRootNodeAsserter atomRootNodeAsserter;
	private final NodeEvaluator nodeEvaluator;

	public List<Node> evaluate(
		final List<Node> rootNodes,
		final List<UserDefinedFunction> userDefinedFunctions,
		final Map<String, Node> variableNameToValueMap
	) throws Exception{
		List<Node> evaluatedNodes = new ArrayList<>();
		for(Node node: rootNodes) {
			boolean isNotList = !expressionNodeDeterminer.isExpressionNode(node);
			if (isNotList) {
				AtomNode atomNode = (AtomNode)node;
				atomRootNodeAsserter.assertAtomRootNode(atomNode);
			}
			Node evaluatedNode = nodeEvaluator.evaluate(
				node,
				userDefinedFunctions,
				variableNameToValueMap,
				false
			);
			evaluatedNodes.add(evaluatedNode);
		}
		return evaluatedNodes;
	}
}
