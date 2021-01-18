package pt.iscte.paddle.model;

import java.util.List;

public interface IBreak extends IStatement {
	
	IBlock getParent();
	
	@Override
	default List<IExpression> getExpressionParts() {
		return List.of();
	}
	
	
	@Override
	default boolean isSame(IProgramElement s) {
		return s instanceof IBreak;
	}
}