package pt.iscte.paddle.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.model.impl.ArrayType;
import pt.iscte.paddle.model.impl.PrimitiveType;

public interface IType extends IProgramElement  {

	Object getDefaultValue();

	int getMemoryBytes();

	// TODO REVER
	default boolean isCompatible(IType type) {
		return isSame(type);
	}
	
//	boolean isSame(IType type);
	
//	{
//		if(isValueType() && type.isValueType())
//			return this.equals(type);
//		else if(isReference() && type.isReference())
//			return ((IReferenceType) this).getTarget().isSame(((IReferenceType) type).getTarget());
//	}
		
	default boolean isVoid() {
		return this == VOID;
	}
	
	default boolean isValueType() {
		return this instanceof IValueType;
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
	IValueType BOOLEAN = PrimitiveType.BOOLEAN;
	// TODO IDataType CHAR

	ImmutableCollection<IValueType> VALUE_TYPES = ImmutableList.of(INT, DOUBLE, BOOLEAN);

	static IType match(String type) {		
		for(IValueType t : VALUE_TYPES)
			if(t.getId().equals(type))
				return t;
		return null;
	}
	
	public static Set<IType> getAllTypes() {
		return Collections.unmodifiableSet(Instances.mapArrayTypes.keySet());
	}
	
	// TODO to impl
	class Instances {
		private static Map<IType, IArrayType> mapArrayTypes = new HashMap<>();
		
		
	}
	
	
	default IArrayType array() {
		IArrayType type = Instances.mapArrayTypes.get(this);
		if(type == null) {
			type = new ArrayType(this);
			Instances.mapArrayTypes.put(this, type);
		}
		return type;
	}
	
	default IArrayType array2D() {
		return array().array();
	}
	
	IExpression getDefaultExpression();
	
	IType VOID = new IType() {
		
		@Override
		public void setProperty(Object key, Object value) {

		}

		@Override
		public Object getProperty(Object key) {
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
		
		@Override
		public String getId() {
			return "void";
		}
		
		@Override
		public IExpression getDefaultExpression() {
			throw new UnsupportedOperationException();
		}
	};


	IType UNBOUND = new IType() {
		
		@Override
		public Object getDefaultValue() {
			return null;
		}

		@Override
		public String toString() {
			return "unknown";
		}

		@Override
		public String getId() {
			return "Type";
		}

		@Override
		public int getMemoryBytes() {
			return 0;
		}
		@Override
		public void setProperty(Object key, Object value) {

		}
		@Override
		public Object getProperty(Object key) {
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
		
		@Override
		public IExpression getDefaultExpression() {
			return ILiteral.NULL;
		}
	};
}