package pt.iscte.paddle.model.impl;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.interpreter.impl.Value;
import pt.iscte.paddle.model.IBinaryExpression;
import pt.iscte.paddle.model.IBinaryOperator;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IType;

public enum ArithmeticOperator implements IBinaryOperator {
	ADD("+", (left, right) -> left.add(right)), 
	SUB("-", (left, right) -> left.subtract(right)),
	MUL("*", (left, right) -> left.multiply(right)),
	DIV("/", (left, right) -> left.divide(right)),
	IDIV("//", (left, right) -> left.divideToIntegralValue(right)),
	MOD("%", (left, right ) -> left.remainder(right));
	
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
			return IType.UNBOUND;
	}
	
	@Override
	public boolean isValidFor(IExpression left, IExpression right) {
		return left.getType().isNumber() && right.getType().isNumber();
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
	public void setProperty(Object key, Object value) {
		programElement.setProperty(key, value);
	}
	
	@Override
	public Object getProperty(Object key) {
		return programElement.getProperty(key);
	}
	
	@Override
	public void addPropertyListener(IPropertyListener listener) {
		programElement.addPropertyListener(listener);
	}
}
