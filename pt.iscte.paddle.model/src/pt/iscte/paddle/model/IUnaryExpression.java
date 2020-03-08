package pt.iscte.paddle.model;

import pt.iscte.paddle.model.impl.UnaryExpression;


public interface IUnaryExpression extends ICompositeExpression {
	IUnaryOperator getOperator();
	IExpression getOperand();
	
	@Override
	default boolean includes(IVariableDeclaration variable) {
		return getOperand().includes(variable);
	}
	
	@Override
	default boolean isSame(IExpression e) {
		return e instanceof IUnaryExpression &&
				getOperator().equals(((IUnaryExpression) e).getOperator()) &&
				getOperand().isSame(((IUnaryExpression) e).getOperand());
	}
	
	static IUnaryExpression create(IUnaryOperator operator, IExpression target) {
		return new UnaryExpression(operator, target);
	}
}
