package pt.iscte.paddle.model.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.IReferenceType;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IValueType;

public enum PrimitiveType implements IValueType {
	INT {
		public boolean matchesLiteral(String literal) {
			try {
				Integer.parseInt(literal);
				return true;
			}
			catch(NumberFormatException e) {
				return false;
			}
		}

		public Object create(String literal) {
			return new BigDecimal(literal);
		}

		@Override
		public Object getDefaultValue() {
			return new BigDecimal(0);
		}

		@Override
		public boolean matchesPrimitiveType(Class<?> clazz) {
			return clazz.equals(int.class);
		}

		public boolean matches(Object object) {
			return Integer.class.isInstance(object) || object instanceof BigDecimal && isWhole((BigDecimal)  object);
		}

		private boolean isWhole(BigDecimal bigDecimal) {
			return bigDecimal.setScale(0, RoundingMode.HALF_UP).compareTo(bigDecimal) == 0;
		}

		@Override
		public int getMemoryBytes() {
			return 4;
		}

		@Override
		public ILiteral literal(Object obj) {
			return new Literal(IType.INT, Integer.toString((Integer) obj));
		}
		
		@Override
		public IExpression getDefaultExpression() {
			return IType.INT.literal(0);
		}
	}, 
	DOUBLE {
		public boolean matchesLiteral(String literal) {
			try {
				Double.parseDouble(literal);
				return true;
			}
			catch(NumberFormatException e) {
				return false;
			}
		}

		@Override
		public boolean matchesPrimitiveType(Class<?> clazz) {
			return clazz.equals(double.class);
		}

		public Object create(String literal) {
			return new BigDecimal(literal);
		}

		@Override
		public Object getDefaultValue() {
			return new BigDecimal("0.0");
		}

		public boolean matches(Object object) {
			return Double.class.isInstance(object);
		}

		@Override
		public int getMemoryBytes() {
			return 8;
		}

		@Override
		public ILiteral literal(Object obj) {
			return new Literal(IType.DOUBLE, Double.toString((Double) obj));
		}
		
		@Override
		public IExpression getDefaultExpression() {
			return IType.DOUBLE.literal(0.0);
		}
	}, 
	BOOLEAN {
		public boolean matchesLiteral(String literal) {
			return literal.matches("true|false");
		}

		public Object create(String literal) {
			return Boolean.valueOf(literal); //.equals("true") ? Boolean.TRUE : Boolean.FALSE;
		}

		@Override
		public boolean matchesPrimitiveType(Class<?> clazz) {
			return clazz.equals(boolean.class);
		}

		public boolean matches(Object object) {
			return Boolean.class.isInstance(object);
		}

		@Override
		public Object getDefaultValue() {
			return Boolean.FALSE;
		}

		@Override
		public int getMemoryBytes() {
			return 1;
		}
		
		@Override
		public ILiteral literal(Object obj) {
			assert obj instanceof Boolean;
			return (Boolean) obj ? new Literal(BOOLEAN, "true") : new Literal(BOOLEAN, "false");
		}
		
		@Override
		public IExpression getDefaultExpression() {
			return IType.BOOLEAN.literal(false);
		}
	};
	
//	private static ILiteral TRUE = new Literal(BOOLEAN, "true");
//	private static ILiteral FALSE = new Literal(BOOLEAN, "false");

	@Override
	public String toString() {
		return name().toLowerCase();
	}

	@Override
	public String getId() {
		return name().toLowerCase();
	}

	@Override
	public abstract boolean matches(Object object);

	@Override
	public abstract boolean matchesLiteral(String literal);

	@Override
	public abstract Object create(String literal);

	@Override
	public boolean isCompatible(IType type) {
		return this.equals(type); // TODO number compatible
	}

	@Override
	public Object getProperty(Object key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setProperty(Object key, Object value) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void addPropertyListener(IPropertyListener listener) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public IReferenceType reference() {
		return new ReferenceType(this);
	}
}