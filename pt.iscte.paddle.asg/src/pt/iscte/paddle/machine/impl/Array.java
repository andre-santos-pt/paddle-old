package pt.iscte.paddle.machine.impl;

import pt.iscte.paddle.asg.IArrayType;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.IValue;

class Array implements IArray {
	private final IArrayType type;
	private final IValue[] elements;
	
	public Array(IArrayType type, int length) {
		this.type = type;
		elements = new IValue[length];
		for(int i = 0; i < length; i++)
			this.elements[i] = IValue.NULL;
	}
	
	@Override
	public IArrayType getType() {
		return type;
	}

	@Override
	public IArray copy() {
		Array copy = new Array(type, elements.length);
		if(elements != null) {
			for(int i = 0; i < elements.length; i++)
				copy.elements[i] = elements[i];
		}
		return copy;
	}
	
	@Override
	public int getLength() {
		assert !isNull();
		return elements.length;
	}

	@Override
	public IValue getElement(int i) {
		assert !isNull();
		if(elements[i] == null)
			return IValue.NULL;
		else
			return elements[i];
	}
	
	@Override
	public void setElement(int i, IValue value) {
		assert !isNull();
		elements[i] = value;
	}
	
	@Override
	public Object getValue() {
		return elements;
	}
	
	@Override
	public String toString() {
		if(isNull())
			return "null";
		
		String text = "[";
		for(int i = 0; i < elements.length; i++) {
			if(i != 0)
				text += ", ";
			text += elements[i];
		}
		return text + "]";
	}
}
