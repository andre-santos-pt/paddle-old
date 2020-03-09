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
	
	@Override
	default boolean includes(IVariableDeclaration variable) {
		return getLeftOperand().includes(variable) || getRightOperand().includes(variable);
	}
	
	@Override
	default boolean isSame(IProgramElement e) {
		return e instanceof IBinaryExpression &&
				getOperator().equals(((IBinaryExpression) e).getOperator()) &&
				getLeftOperand().isSame(((IBinaryExpression) e).getLeftOperand()) &&
				getRightOperand().isSame(((IBinaryExpression) e).getRightOperand());
	}
	
	static IBinaryExpression create(IBinaryOperator operator, IExpression leftOperand, IExpression rightOperand) {
		return new BinaryExpression(operator, leftOperand, rightOperand);
	}
}

