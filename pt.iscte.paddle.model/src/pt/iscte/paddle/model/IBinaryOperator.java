package pt.iscte.paddle.model;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.impl.ArithmeticOperator;

public interface IBinaryOperator extends IOperator {
	
	boolean isValidFor(IExpression left, IExpression right);
	
	IType getResultType(IExpression left, IExpression right);
	
	IValue apply(IValue left, IValue right) throws ExecutionError;
	
	IBinaryExpression on(IExpression leftOperand, IExpression rightOperand);
	
	default boolean isArithmetic() {
		return this instanceof ArithmeticOperator;
	}
}
