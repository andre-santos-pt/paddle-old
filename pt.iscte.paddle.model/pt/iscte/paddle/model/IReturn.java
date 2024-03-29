package pt.iscte.paddle.model;

import java.util.Collections;
import java.util.List;

public interface IReturn extends IStatement {
	IExpression getExpression(); // may be null (void)
	IBlock getParent();
	boolean isError();

	default boolean isVoid() {
		return getExpression() == null;
		
	}
	default IType getReturnValueType() {
		return isVoid() ? IType.VOID : getExpression().getType();
	}
	
	@Override
	default List<IExpression> getExpressionParts() {
		if(getExpression() == null)
			return Collections.emptyList();
		else
			return List.of(getExpression());
	}
	
	@Override
	default boolean isSame(IProgramElement s) {
		return s instanceof IReturn &&
				(isVoid() && ((IReturn) s).isVoid() ||
				getExpression().isSame(((IReturn) s).getExpression())
				);
	}
}
