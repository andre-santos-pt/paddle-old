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

public enum ArithmeticOperator implements IBinaryOperator {
	ADD("+", (left, right) -> left.add(right)), 
	SUB("-", (left, right) -> left.subtract(right)),
	MUL("*", (left, right) -> left.multiply(right)),
	DIV("/", (left, right) -> left.divide(right)),
	MOD("%", (left, right) -> left.remainder(right));
	
	private final BiFunction<BigDecimal, BigDecimal, BigDecimal> f;
	private ProgramElement programElement;
	
	private ArithmeticOperator(String symbol, BiFunction<BigDecimal, BigDecimal, BigDecimal> f) {
		this.f = f;
		programElement = new ProgramElement();
		setId(symbol);
	}
	
	private static IType getDataType(IType left, IType right) {
		if(left.equals(IType.INT) && right.equals(IType.INT))
			return IType.INT;
		else if(left.equals(IType.DOUBLE) && right.equals(IType.INT))
			return IType.DOUBLE;
		else if(left.equals(IType.INT) && right.equals(IType.DOUBLE))
			return IType.DOUBLE;
		else if(left.equals(IType.DOUBLE) && right.equals(IType.DOUBLE))
			return IType.DOUBLE;
		else
			return IType.UNKNOWN;
	}
	
	@Override
	public boolean isValidFor(IType left, IType right) {
		return left.isNumber() && right.isNumber();
	}
	
	@Override
	public IValue apply(IValue left, IValue right) throws ExecutionError {
		IType type = getDataType(left.getType(), right.getType());
		BigDecimal obj = f.apply((BigDecimal) left.getValue(), (BigDecimal) right.getValue());
		return Value.create(type, obj);
	}
	
	public IType getResultType(IExpression left, IExpression right) {
		return getDataType(left.getType(), right.getType());
	}
	
	@Override
	public OperationType getOperationType() {
		return OperationType.ARITHMETIC;
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
