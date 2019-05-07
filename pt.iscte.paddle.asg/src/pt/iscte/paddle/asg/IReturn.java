package pt.iscte.paddle.asg;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableList;

public interface IReturn extends IStatement {
	IExpression getExpression(); // may be null (void)
	IBlock getParent();

	
	default IType getReturnValueType() {
		return getExpression() == null ? IType.VOID : getExpression().getType();
	}
	
	@Override
	default List<IExpression> getExpressionParts() {
		if(getExpression() == null)
			return Collections.emptyList();
		else
			return ImmutableList.of(getExpression());
	}
}
