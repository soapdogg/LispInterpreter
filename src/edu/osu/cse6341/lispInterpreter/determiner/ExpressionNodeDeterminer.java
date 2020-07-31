package edu.osu.cse6341.lispInterpreter.determiner;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class ExpressionNodeDeterminer {

    public boolean isExpressionNode(LispNode node) {
        return node instanceof ExpressionNode;
    }
}
