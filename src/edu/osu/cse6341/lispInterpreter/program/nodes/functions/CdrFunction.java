package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;

public class CdrFunction extends BaseFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;

	public CdrFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
    }

	private CdrFunction(Node params){
        super(params);
        functionLengthAsserter = new FunctionLengthAsserter();
    }

    @Override
	public Node evaluate() throws Exception{
        functionLengthAsserter.assertLengthIsAsExpected(
            getFunctionName(),
            expectedParameterLength(),
            params.getLength()
        );
        ExpressionNode node = getListValue(((ExpressionNode)params).getAddress().evaluate(false));
        return node.getData();
	}

    @Override
	public BaseFunction newInstance(Node params){
		return new CdrFunction(params);
	}

    @Override
    String getFunctionName() {
        return "CDR";
    }

    @Override
    public Node evaluateLispFunction() throws Exception {
        return evaluate();
    }

    @Override
    public LispFunction newFunctionInstance(Node node) {
        return new CdrFunction(node);
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
