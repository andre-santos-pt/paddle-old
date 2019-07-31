package pt.iscte.paddle.model.impl;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IReference;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.interpreter.impl.Value;
import pt.iscte.paddle.model.IBinaryExpression;
import pt.iscte.paddle.model.IBinaryOperator;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IType;

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
		if(left instanceof IReference)
			left = ((IReference) left).getTarget();
		
		if(right instanceof IReference)
			right = ((IReference) right).getTarget();
		
		return Value.create(IType.BOOLEAN, f.apply(left, right));
	}
	
	@Override
	public IType getResultType(IExpression left, IExpression right) {
		return IType.BOOLEAN;
	}
	
	@Override
	public boolean isValidFor(IExpression left, IExpression right) {
		if(this == EQUAL || this == DIFFERENT)
			return left.isNull() || right.isNull() || left.getType().equals(right.getType());
		else
			return left.getType().isNumber() && right.getType().isNumber();
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
	public void setProperty(Object key, Object value) {
		programElement.setProperty(key, value);
	}
	
	@Override
	public Object getProperty(Object key) {
		return programElement.getProperty(key);
	}
}
