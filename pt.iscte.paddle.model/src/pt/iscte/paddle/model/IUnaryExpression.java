package pt.iscte.paddle.model;

import pt.iscte.paddle.model.impl.UnaryExpression;


public interface IUnaryExpression extends ICompositeExpression {
	IUnaryOperator getOperator();
	IExpression getOperand();
	
	static IUnaryExpression create(IUnaryOperator operator, IExpression target) {
		return new UnaryExpression(operator, target);
	}
}
