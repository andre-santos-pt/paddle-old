package pt.iscte.paddle.asg;

import pt.iscte.paddle.machine.IValue;

public interface IUnaryOperator extends IOperator {
	boolean isValidFor(IType type);
	IType getResultType(IExpression exp);	
	IValue apply(IValue value);
	
	IUnaryExpression on(IExpression exp);
}
