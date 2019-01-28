package pt.iscte.paddle.asg.semantics;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IBreak;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProgramElement;

public class BreakContinueLocation extends Rule {

	@Override
	public void visit(IBreak breakStatement) {
		IProgramElement b = breakStatement.getParent();
		while(!(b instanceof IProcedure)) {
			if(b instanceof ILoop)
				return;
			b = ((IBlock) b).getParent();
		}
		addProblem(ISemanticProblem.create("break statement can only be used inside a loop", breakStatement));
	}
}
