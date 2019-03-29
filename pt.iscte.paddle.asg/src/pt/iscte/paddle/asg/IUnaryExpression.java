package pt.iscte.paddle.asg;

public interface IUnaryExpression extends ICompositeExpression {
	IUnaryOperator getOperator();
	IExpression getOperand();
}
