package pt.iscte.paddle.asg;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.asg.impl.PrimitiveType;

public interface IDataType extends IIdentifiableElement {

	Object getDefaultValue();

	int getMemoryBytes();

	default boolean isCompatible(IDataType type) {
		return this.getId().equals(type.getId());
	}
	
	default boolean isVoid() {
		return this == VOID;
	}

	IReferenceType referenceType();
	
	IValueType INT = PrimitiveType.INT;
	IValueType DOUBLE = PrimitiveType.DOUBLE;
	IValueType BOOLEAN =PrimitiveType.BOOLEAN;
	// TODO IDataType CHAR

	ImmutableCollection<IValueType> VALUE_TYPES = ImmutableList.of(INT, DOUBLE, BOOLEAN);

	
	IDataType VOID = new IDataType() {

		@Override
		public String getId() {
			return "void";
		}

		@Override
		public void setProperty(String key, Object value) {

		}

		@Override
		public Object getProperty(String key) {
			return null;
		}

		@Override
		public IReferenceType referenceType() {
			throw new RuntimeException("not valid");
		}
		
//		@Override
//		public boolean matchesPrimitiveType(Class<?> clazz) {
//			return clazz.equals(void.class);
//		}
//
//		@Override
//		public boolean matches(Object object) {
//			return false;
//		}
//
//		@Override
//		public boolean matchesLiteral(String literal) {
//			return false;
//		}
//
//		@Override
//		public Object create(String literal) {
//			return null;
//		}

		@Override
		public Object getDefaultValue() {
			return null;
		}

		@Override
		public int getMemoryBytes() {
			return 0;
		}

		@Override
		public String toString() {
			return "void";
		}
	};


	IDataType UNKNOWN = new IDataType() {
		@Override
		public String getId() {
			return "unknown";
		}
//		@Override
//		public boolean matchesPrimitiveType(Class<?> clazz) {
//			return false;
//		}
//
//		@Override
//		public boolean matches(Object object) {
//			return false;
//		}
//		@Override
//		public boolean matchesLiteral(String literal) {
//			return false;
//		}
//		@Override
//		public Object create(String literal) {
//			return null;
//		}

		@Override
		public Object getDefaultValue() {
			return null;
		}

		@Override
		public String toString() {
			return getId();
		}

		@Override
		public int getMemoryBytes() {
			return 0;
		}
		@Override
		public void setProperty(String key, Object value) {

		}
		@Override
		public Object getProperty(String key) {
			return null;
		}
		
		@Override
		public IReferenceType referenceType() {
			throw new RuntimeException("not valid");
		}
	};
}