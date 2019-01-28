package pt.iscte.paddle.asg;

import pt.iscte.paddle.machine.IValue;

public interface IUnaryOperator extends IOperator {
	IDataType getResultType(IExpression exp);	
	IValue apply(IValue value);
}
