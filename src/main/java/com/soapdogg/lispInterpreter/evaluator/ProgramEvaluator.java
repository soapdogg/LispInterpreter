package com.soapdogg.lispInterpreter.evaluator;

import com.soapdogg.lispInterpreter.asserter.AtomRootNodeAsserter;
import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProgramEvaluator {

	private final ExpressionNodeDeterminer expressionNodeDeterminer;
	private final AtomRootNodeAsserter atomRootNodeAsserter;
	private final NodeEvaluator nodeEvaluator;

	public ProgramEvaluator(
	    ExpressionNodeDeterminer expressionNodeDeterminer,
        AtomRootNodeAsserter atomRootNodeAsserter,
        NodeEvaluator nodeEvaluator
    ) {
	    this.expressionNodeDeterminer = expressionNodeDeterminer;
	    this.atomRootNodeAsserter = atomRootNodeAsserter;
	    this.nodeEvaluator = nodeEvaluator;
    }

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
