package pt.iscte.paddle.asg;

import java.util.List;

import com.google.common.collect.ImmutableList;

public interface IBreak extends IStatement {
	@Override
	default List<IExpression> getExpressionParts() {
		return ImmutableList.of();
	}
}