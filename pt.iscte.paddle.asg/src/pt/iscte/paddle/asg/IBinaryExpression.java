package pt.iscte.paddle.asg;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.asg.IOperator.OperationType;
import pt.iscte.paddle.asg.impl.BinaryExpression;

public interface IBinaryExpression extends ICompositeExpression {
	IBinaryOperator getOperator();
	IExpression getLeftOperand();
	IExpression getRightOperand();
	
	@Override
	default OperationType getOperationType() {
		return getOperator().getOperationType();
	}
	
	@Override
	default List<IExpression> decompose() {
		return ImmutableList.of(getLeftOperand(), getRightOperand());
	}
	
	static IBinaryExpression create(IBinaryOperator operator, IExpression leftOperand, IExpression rightOperand) {
		return new BinaryExpression(operator, leftOperand, rightOperand);
	}
}

