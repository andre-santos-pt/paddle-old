package pt.iscte.paddle.model;

import pt.iscte.paddle.interpreter.IValue;

public interface IUnaryOperator extends IOperator {
	boolean isValidFor(IType type);
	IType getResultType(IExpression exp);	
	IValue apply(IValue value);
	
	IUnaryExpression on(IExpression exp);
}
