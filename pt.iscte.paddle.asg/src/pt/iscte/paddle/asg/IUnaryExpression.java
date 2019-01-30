package pt.iscte.paddle.asg;

import java.util.List;

import com.google.common.collect.ImmutableList;

public interface IUnaryExpression extends ICompositeExpression {
	IUnaryOperator getOperator();
	IExpression getOperand();
}
