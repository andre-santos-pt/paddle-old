package pt.iscte.paddle.machine.impl;

import pt.iscte.paddle.asg.IArrayType;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IValueType;
import pt.iscte.paddle.machine.IValue;

public final class Value implements IValue {
	private final IDataType type;
	private Object value;
	
	private Value(IDataType type, Object value) {
		this.type = type;
		this.value = value;
	}

	public static IValue create(IDataType type, Object value) {
		if(type instanceof IValueType)
			return new Value(type, ((IValueType)type).create(value.toString()));			
		else if(type instanceof IArrayType)
			return new Array((IArrayType) type);
		else
			return new Value(type, value);
	}
	
	@Override
	public IDataType getType() {
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
	
	@Override
	public void setValue(Object value) {
//		assert !(value instanceof IValue);
		this.value = value;
	}
	
	@Override
	public IValue copy() {
		return new Value(type, value);
	}
}
