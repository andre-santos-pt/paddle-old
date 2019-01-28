package pt.iscte.paddle.asg;

public interface IValueType extends IDataType {
	boolean matchesPrimitiveType(Class<?> clazz);

	boolean matches(Object object);

	boolean matchesLiteral(String literal);

	// pre: matchesLiteral(literal)
	Object create(String literal);
	
	default boolean isNumeric() {
		return this.equals(INT) || this.equals(DOUBLE);
	}

	default boolean isBoolean() {
		return this.equals(BOOLEAN);
	}

	
//	enum DefaultValueType implements IValueType {
//		INT {
//			public boolean matchesLiteral(String literal) {
//				try {
//					Integer.parseInt(literal);
//					return true;
//				}
//				catch(NumberFormatException e) {
//					return false;
//				}
//			}
//
//			public Object create(String literal) {
//				return new BigDecimal(literal);
//			}
//
//			@Override
//			public Object getDefaultValue() {
//				return new BigDecimal(0);
//			}
//
//			@Override
//			public boolean matchesPrimitiveType(Class<?> clazz) {
//				return clazz.equals(int.class);
//			}
//
//			public boolean matches(Object object) {
//				return Integer.class.isInstance(object) || object instanceof BigDecimal && isWhole((BigDecimal)  object);
//			}
//
//			private boolean isWhole(BigDecimal bigDecimal) {
//				return bigDecimal.setScale(0, RoundingMode.HALF_UP).compareTo(bigDecimal) == 0;
//			}
//
//			@Override
//			public int getMemoryBytes() {
//				return 4;
//			}
//		}, 
//		DOUBLE {
//			public boolean matchesLiteral(String literal) {
//				try {
//					Double.parseDouble(literal);
//					return true;
//				}
//				catch(NumberFormatException e) {
//					return false;
//				}
//			}
//
//			@Override
//			public boolean matchesPrimitiveType(Class<?> clazz) {
//				return clazz.equals(double.class);
//			}
//
//			public Object create(String literal) {
//				return new BigDecimal(literal);
//			}
//
//			@Override
//			public Object getDefaultValue() {
//				return new BigDecimal("0.0");
//			}
//
//			public boolean matches(Object object) {
//				return Double.class.isInstance(object);
//			}
//
//			@Override
//			public int getMemoryBytes() {
//				return 8;
//			}
//		}, 
//		BOOLEAN {
//			public boolean matchesLiteral(String literal) {
//				return literal.matches("true|false");
//			}
//
//			public Object create(String literal) {
//				return new Boolean(literal); //.equals("true") ? Boolean.TRUE : Boolean.FALSE;
//			}
//
//			@Override
//			public boolean matchesPrimitiveType(Class<?> clazz) {
//				return clazz.equals(boolean.class);
//			}
//
//			public boolean matches(Object object) {
//				return Boolean.class.isInstance(object);
//			}
//
//			@Override
//			public Object getDefaultValue() {
//				return Boolean.FALSE;
//			}
//
//			@Override
//			public int getMemoryBytes() {
//				return 1;
//			}
//		};
//
//		@Override
//		public String toString() {
//			return name().toLowerCase();
//		}
//
//		@Override
//		public String getId() {
//			return name().toLowerCase();
//		}
//
//		@Override
//		public abstract boolean matches(Object object);
//
//		@Override
//		public abstract boolean matchesLiteral(String literal);
//
//		@Override
//		public abstract Object create(String literal);
//
//		@Override
//		public boolean isCompatible(IDataType type) {
//			return this.equals(type); // TODO number compatible
//		}
//
//		@Override
//		public Object getProperty(String key) {
//			throw new UnsupportedOperationException();
//		}
//
//		@Override
//		public void setProperty(String key, Object value) {
//			throw new UnsupportedOperationException();
//		}
//	}

}