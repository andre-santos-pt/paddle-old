package pt.iscte.paddle.asg.impl;

import java.math.BigDecimal;

import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IUnaryExpression;
import pt.iscte.paddle.asg.IUnaryOperator;
import pt.iscte.paddle.machine.IValue;
import pt.iscte.paddle.machine.impl.Value;

public enum UnaryOperator implements IUnaryOperator {
	NOT("!") {
		@Override
		protected Object calculate(IValue value) {
			assert value.getType() == IType.BOOLEAN;
			return !(boolean) value.getValue();
		}
		
		@Override
		public IType getResultType(IExpression exp) {
			return IType.BOOLEAN;
		}
		
		@Override
		public OperationType getOperationType() {
			return OperationType.LOGICAL;
		}
		
		@Override
		public boolean isValidFor(IType type) {
			return type.isBoolean();
		}
	},
	TRUNCATE("(int)") {
		@Override
		protected Object calculate(IValue value) {
			return new BigDecimal(((BigDecimal) value.getValue()).intValue());
		}
		
		@Override
		public IType getResultType(IExpression exp) {
			return IType.INT;
		}
		
		@Override
		public OperationType getOperationType() {
			return OperationType.ARITHMETIC;
		}
		
		@Override
		public boolean isValidFor(IType type) {
			return type.isNumber();
		}
	},
	
	// TODO NEGATION
	// TODO PLUS
	;
	
	private final ProgramElement programElement;
	
	private UnaryOperator(String symbol) {
		programElement = new ProgramElement();
		setId(symbol);
	}
	
	@Override
	public IValue apply(IValue value) {
		Object obj = calculate(value);
		return Value.create(getResultType(null), obj);
	}
	
	@Override
	public IUnaryExpression on(IExpression exp) {
		return new UnaryExpression(this, exp);
	}
	
	protected abstract Object calculate(IValue value);
	
	public abstract IType getResultType(IExpression exp);
	
	@Override
	public void setProperty(Object key, Object value) {
		programElement.setProperty(key, value);
	}
	
	@Override
	public Object getProperty(Object key) {
		return programElement.getProperty(key);
	}
}
