package pt.iscte.paddle.model;

import pt.iscte.paddle.model.IOperator.OperationType;
import pt.iscte.paddle.model.impl.BinaryExpression;

public interface IBinaryExpression extends ICompositeExpression {
	IBinaryOperator getOperator();
	IExpression getLeftOperand();
	IExpression getRightOperand();
	
	@Override
	default OperationType getOperationType() {
		return getOperator().getOperationType();
	}
	
	static IBinaryExpression create(IBinaryOperator operator, IExpression leftOperand, IExpression rightOperand) {
		return new BinaryExpression(operator, leftOperand, rightOperand);
	}
}

