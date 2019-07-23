package pt.iscte.paddle.interpreter.impl;

import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IArrayType;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IValueType;

public final class Value implements IValue {
	private final IType type;
	private final Object value;
	
	private Value(IType type, Object value) {
		this.type = type;
		this.value = value;
	}

	public static IValue create(IType type, Object value) {
		if(type instanceof IValueType)
			return new Value(type, ((IValueType)type).create(value.toString()));			
		else if(type instanceof IArrayType)
			return IValue.NULL;
		else
			return new Value(type, value);
	}
	
	@Override
	public IType getType() {
		return type;
	}

	@Override
	public Object getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return value == null ? "null" : value.toString();
	}
	
//	@Override
//	public void setValue(Object value) {
//		assert !(value instanceof IValue);
//		this.value = value;
//	}
	
	@Override
	public IValue copy() {
		return this;
	}
}
