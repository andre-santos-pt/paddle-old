package pt.iscte.paddle.model.validation;

import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IBreak;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProgramElement;

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
