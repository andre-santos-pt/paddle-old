package pt.iscte.paddle.model;

public interface IUnaryExpression extends ICompositeExpression {
	IUnaryOperator getOperator();
	IExpression getOperand();
}
