package pt.iscte.paddle.asg;

import java.util.List;

import com.google.common.collect.ImmutableList;

public interface IUnaryExpression extends IExpression {
	IUnaryOperator getOperator();
	IExpression getOperand();
	
	@Override
	default List<IExpression> decompose() {
		return ImmutableList.of(getOperand());
	}
}
