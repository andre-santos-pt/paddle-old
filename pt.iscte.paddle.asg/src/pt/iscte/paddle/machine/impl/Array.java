package pt.iscte.paddle.machine.impl;

import pt.iscte.paddle.asg.IArrayType;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.IValue;

class Array implements IArray {
	private final IArrayType type;
	private IValue[] elements;
	
	public Array(IArrayType type) {
		this.type = type;
	}
	
	@Override
	public IArrayType getType() {
		return type;
	}

	@Override
	public void setValue(Object array) {
		assert array instanceof IValue[];
		
		IValue[] vals = (IValue[]) array;
		this.elements = new IValue[vals.length];
		for(int i = 0; i < vals.length; i++)
			this.elements[i] = vals[i];
	}
	
	@Override
	public IArray copy() {
		Array copy = new Array(type);
		if(elements != null) {
			copy.elements = new IValue[elements.length];
			for(int i = 0; i < elements.length; i++)
				copy.elements[i] = elements[i].copy();
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
		if(elements[i] == null)
			elements[i] = value;
		else
			elements[i].setValue(value.getValue());
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
