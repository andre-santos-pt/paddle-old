package pt.iscte.paddle.asg.impl;

import pt.iscte.paddle.asg.IArrayType;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IReferenceType;

public class ArrayType extends ProgramElement implements IArrayType {
	private final IDataType type;
	private final int dimensions;
	private final String id;

	public ArrayType(IDataType type, int dimensions) {
		this.type = type;
		this.dimensions = dimensions;
		String id = type.getId();
		for(int i = 0; i < dimensions; i++)
			id += "[]";
		this.id = id;
	}
	
	@Override
	public int getDimensions() {
		return dimensions;
	}
	

	@Override
	public String getId() {
		return id;
	}

	public boolean sameAs(IArrayType type) {
		return id.equals(type.getId());
	}

	@Override
	public String toString() {
		return id;
	}

	@Override
	public Object getDefaultValue() {
		return null;
	}

	@Override
	public IDataType getComponentType() {
		return type;
	}

	@Override
	public int getMemoryBytes() {
		return 4;
	}
	
	@Override
	public IReferenceType referenceType() {
		return new ReferenceType(this);
	}
}