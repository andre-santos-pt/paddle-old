package pt.iscte.paddle.asg.impl;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IBinaryOperator;
import pt.iscte.paddle.asg.IDataType;
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
	
	private static IDataType getDataType(IDataType left, IDataType right) {
		if(left.equals(IDataType.INT) && right.equals(IDataType.INT))
			return IDataType.INT;
		else if(left.equals(IDataType.DOUBLE) && right.equals(IDataType.INT))
			return IDataType.DOUBLE;
		else if(left.equals(IDataType.INT) && right.equals(IDataType.DOUBLE))
			return IDataType.DOUBLE;
		else if(left.equals(IDataType.DOUBLE) && right.equals(IDataType.DOUBLE))
			return IDataType.DOUBLE;
		else
			return IDataType.UNKNOWN;
	}
	
	@Override
	public boolean isValidFor(IDataType left, IDataType right) {
		return left.isNumber() && right.isNumber();
	}
	
	@Override
	public IValue apply(IValue left, IValue right) throws ExecutionError {
		IDataType type = getDataType(left.getType(), right.getType());
		BigDecimal obj = f.apply((BigDecimal) left.getValue(), (BigDecimal) right.getValue());
		return Value.create(type, obj);
	}
	
	public IDataType getResultType(IExpression left, IExpression right) {
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
