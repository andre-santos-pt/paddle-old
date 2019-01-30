package pt.iscte.paddle.asg;

import java.util.List;

/**
 * Immutable
 */
public interface IStatement extends IInstruction {
	
	IBlock getParent();

	List<IExpression> getExpressionParts();
	
}
