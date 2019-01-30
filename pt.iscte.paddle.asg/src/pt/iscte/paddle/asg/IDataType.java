package pt.iscte.paddle.asg;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.asg.impl.ArrayType;
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
	
	default boolean isNumber() {
		return this == INT || this == DOUBLE;
	}

	default boolean isBoolean() {
		return this == BOOLEAN;
	}
	
	default boolean isReference() {
		return this instanceof IReferenceType;
	}
	
	IReferenceType reference();
	
	IValueType INT = PrimitiveType.INT;
	IValueType DOUBLE = PrimitiveType.DOUBLE;
	IValueType BOOLEAN =PrimitiveType.BOOLEAN;
	// TODO IDataType CHAR

	ImmutableCollection<IValueType> VALUE_TYPES = ImmutableList.of(INT, DOUBLE, BOOLEAN);

	// TODO to impl
	class Singletons {
		private static final IArrayType INT_ARRAY = new ArrayType(INT);
		private static final IArrayType INT_ARRAY2D = new ArrayType(new ArrayType(INT));
		
	}
	default IArrayType array() {
		if(this == INT)
			return Singletons.INT_ARRAY;
		else if(this == Singletons.INT_ARRAY)
			return Singletons.INT_ARRAY2D;
		else
			return new ArrayType(this);
	}
	
	default IArrayType array2D() {
		if(this == INT)
			return Singletons.INT_ARRAY2D;
		else
			return new ArrayType(new ArrayType(this));
	}
	
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
		public IReferenceType reference() {
			throw new RuntimeException("not valid");
		}
		
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
		
		@Override
		public IArrayType array() {
			throw new UnsupportedOperationException();
		}
	};


	IDataType UNKNOWN = new IDataType() {
		@Override
		public String getId() {
			return "unknown";
		}

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
		public IReferenceType reference() {
			throw new RuntimeException("not valid");
		}
		
		@Override
		public IArrayType array() {
			throw new UnsupportedOperationException();
		}
	};
}