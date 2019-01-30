package pt.iscte.paddle.asg;

import java.util.List;

import com.google.common.collect.ImmutableList;

public interface IContinue extends IStatement {
	IBlock getParent();

	@Override
	default List<IExpression> getExpressionParts() {
		return ImmutableList.of();
	}
}