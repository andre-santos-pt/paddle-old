package pt.iscte.paddle.asg.impl;

import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IReferenceType;

public class ReferenceType extends ProgramElement implements IReferenceType {

	private final IDataType target;
	
	public ReferenceType(IDataType target) {
		this.target = target;
	}
	
	@Override
	public Object getDefaultValue() {
		return null;
	}

	@Override
	public int getMemoryBytes() {
		return 4;
	}

	@Override
	public IDataType getTarget() {
		return target;
	}
	
	@Override
	public String toString() {
		return getId();
	}
	
	@Override
	public IReferenceType referenceType() {
		throw new UnsupportedOperationException();
	}

}
