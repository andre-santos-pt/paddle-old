package pt.iscte.paddle.model;

import java.util.List;

public interface IArrayAccess extends IProgramElement, ITargetExpression {
	ITargetExpression getTarget();
	List<IExpression> getIndexes(); // not null, length > 0 && length <= getDimensions
	
	@Override
	default boolean isSame(IProgramElement e) {
		return e instanceof IArrayAccess &&
				getTarget().isSame(e) && IExpression.areSame(getIndexes(), ((IArrayAccess) e).getIndexes());
	}
}
