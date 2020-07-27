package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.program.nodes.functions.valueretriver.ListValueRetriever;

public class CdrFunction extends BaseFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final ListValueRetriever listValueRetriever;

	public CdrFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
	    listValueRetriever = new ListValueRetriever();
    }

    @Override
    String getFunctionName() {
        return "CDR";
    }

    @Override
    public Node evaluateLispFunction(Node params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            getFunctionName(),
            expectedParameterLength(),
            params.getLength()
        );
        ExpressionNode node = listValueRetriever.retrieveListValue(
            ((ExpressionNode) params).getAddress().evaluate(false),
            getLispFunctionName()
        );
        return node.getData();
    }

    @Override
    public LispFunction newFunctionInstance() {
        return new CdrFunction();
    }

    @Override
    public String getLispFunctionName() {
        return getFunctionName();
    }

    @Override
    public int expectedParameterLength() {
        return 2;
    }
}
