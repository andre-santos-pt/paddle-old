package pt.iscte.paddle.asg;

import pt.iscte.paddle.machine.IValue;

public interface IUnaryOperator extends IOperator {
	boolean isValidFor(IDataType type);
	IDataType getResultType(IExpression exp);	
	IValue apply(IValue value);
	
	IUnaryExpression on(IExpression exp);
}
