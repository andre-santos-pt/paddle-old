package pt.iscte.paddle.model;

import java.util.List;

/**
 * Immutable
 */
public interface IStatement extends IBlockElement {
	
	List<IExpression> getExpressionParts();
	
}
