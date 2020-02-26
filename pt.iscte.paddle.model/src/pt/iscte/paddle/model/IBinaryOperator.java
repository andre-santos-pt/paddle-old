package pt.iscte.paddle.model;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.impl.ArithmeticOperator;

public interface IBinaryOperator extends IOperator {
	
	boolean isValidFor(IExpression left, IExpression right);
	
	IType getResultType(IExpression left, IExpression right);
	
	IValue apply(IValue left, IValue right) throws ExecutionError;
	
	IBinaryExpression on(IExpression leftOperand, IExpression rightOperand);

//	default IBinaryExpression on(IVariable leftOperand, IExpression rightOperand) {
//		return on(leftOperand.expression(), rightOperand);
//	}
//	
//	default IBinaryExpression on(IExpression leftOperand, IVariable rightOperand) {
//		return on(leftOperand, rightOperand.expression());
//	}
//	
//	default IBinaryExpression on(IVariable leftOperand, IVariable rightOperand) {
//		return on(leftOperand.expression(), rightOperand.expression());
//	}
	
	default IBinaryExpression on(IExpressionView leftOperand, IExpressionView rightOperand) {
		return on(leftOperand.expression(), rightOperand.expression());
	}
	
	default boolean isArithmetic() {
		return this instanceof ArithmeticOperator;
	}
}
