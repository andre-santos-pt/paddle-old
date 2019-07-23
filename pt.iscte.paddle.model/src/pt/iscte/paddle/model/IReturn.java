package pt.iscte.paddle.model;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableList;

public interface IReturn extends IStatement {
	IExpression getExpression(); // may be null (void)
	IBlock getParent();

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
			return ImmutableList.of(getExpression());
	}
}
