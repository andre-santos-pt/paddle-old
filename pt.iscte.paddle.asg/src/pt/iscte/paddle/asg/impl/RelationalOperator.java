package pt.iscte.paddle.asg.impl;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IBinaryOperator;
import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.IValue;
import pt.iscte.paddle.machine.impl.Value;

public enum RelationalOperator implements IBinaryOperator {
	EQUAL("==", (left,right) -> left.getValue().equals(right.getValue())),
	
	DIFFERENT("!=", (left,right) -> !left.getValue().equals(right.getValue())),
	
	GREATER(">", (left, right) -> compare(left, right) > 0),
	
	GREATER_EQUAL(">=", (left, right) -> compare(left, right) >= 0),
	
	SMALLER("<", (left, right) -> compare(left, right) < 0),
	
	SMALLER_EQUAL("<=", (left, right) -> compare(left, right) <= 0);

	private static int compare(IValue left, IValue right) {
		return ((BigDecimal) left.getValue()).compareTo((BigDecimal) right.getValue());
	}
	
	private final ProgramElement programElement;
	
	private final BiFunction<IValue, IValue, Boolean> f;
	
	private RelationalOperator(String symbol, BiFunction<IValue, IValue, Boolean> f) {
		this.f = f;
		programElement = new ProgramElement();
		setId(symbol);
	}

	@Override
	public IValue apply(IValue left, IValue right) throws ExecutionError {
		return Value.create(IType.BOOLEAN, f.apply(left, right));
	}
	
	@Override
	public IType getResultType(IExpression left, IExpression right) {
		return IType.BOOLEAN;
	}
	@Override
	public boolean isValidFor(IType left, IType right) {
		if(this == EQUAL || this == DIFFERENT)
			return left.equals(right);
		else
			return left.isNumber() && right.isNumber();
	}

	@Override
	public OperationType getOperationType() {
		return OperationType.RELATIONAL;
	}
	
	@Override
	public IBinaryExpression on(IExpression leftOperand, IExpression rightOperand) {
		return new BinaryExpression(this, leftOperand, rightOperand);
	}
	
	@Override
	public void setProperty(String key, Object value) {
		programElement.setProperty(key, value);
	}
	
	@Override
	public Object getProperty(String key) {
		return programElement.getProperty(key);
	}
}
