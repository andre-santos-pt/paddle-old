package pt.iscte.paddle.asg;

import java.util.List;

/**
 * Immutable
 */
public interface IStatement extends IBlockElement {
	
	List<IExpression> getExpressionParts();
	
}
