package pt.iscte.paddle.asg;

import java.util.List;

/**
 * Immutable
 */
public interface IStatement extends IBlockChild {
	
	List<IExpression> getExpressionParts();
	
}
