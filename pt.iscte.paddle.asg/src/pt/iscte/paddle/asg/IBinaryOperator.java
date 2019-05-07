package pt.iscte.paddle.asg;

import pt.iscte.paddle.asg.impl.ArithmeticOperator;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.IValue;

public interface IBinaryOperator extends IOperator {
	
	boolean isValidFor(IType left, IType right);
	
	IType getResultType(IExpression left, IExpression right);
	
	IValue apply(IValue left, IValue right) throws ExecutionError;
	
	IBinaryExpression on(IExpression leftOperand, IExpression rightOperand);
	
	default boolean isArithmetic() {
		return this instanceof ArithmeticOperator;
	}
}
