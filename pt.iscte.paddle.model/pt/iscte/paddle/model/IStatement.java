package pt.iscte.paddle.model;

import java.util.List;

/**
 * Immutable
 */
public interface IStatement extends IBlockElement, IStep {
	
	List<IExpression> getExpressionParts();

	default boolean isSame(IProgramElement s) {
		return this.getClass().equals(s.getClass()) &&
				IExpression.areSame(getExpressionParts(), ((IStatement) s).getExpressionParts());
	}
}
