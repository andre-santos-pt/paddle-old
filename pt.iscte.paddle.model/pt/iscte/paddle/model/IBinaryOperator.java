package pt.iscte.paddle.model;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.impl.ArithmeticOperator;
import pt.iscte.paddle.model.impl.LogicalOperator;
import pt.iscte.paddle.model.impl.RelationalOperator;

public interface IBinaryOperator extends IOperator {
	
	boolean isValidFor(IExpression left, IExpression right);
	
	IType getResultType(IExpression left, IExpression right);
	
	IValue apply(IValue left, IValue right) throws ExecutionError;
	
	IBinaryExpression on(IExpression leftOperand, IExpression rightOperand);

	default IBinaryExpression on(IExpressionView leftOperand, IExpressionView rightOperand) {
		return on(leftOperand.expression(), rightOperand.expression());
	}
	
	default boolean isArithmetic() {
		return this instanceof ArithmeticOperator;
	}
	
	default boolean isRelational() {
		return this instanceof RelationalOperator;
	}

	default boolean isLogical() {
		return this instanceof LogicalOperator;
	}
	
}
