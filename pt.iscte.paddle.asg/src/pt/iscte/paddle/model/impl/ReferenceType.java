package pt.iscte.paddle.model.impl;

import pt.iscte.paddle.model.IReferenceType;
import pt.iscte.paddle.model.IType;

public class ReferenceType extends ProgramElement implements IReferenceType {

	private final IType target;
	
	public ReferenceType(IType target) {
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
	public IType getTarget() {
		return target;
	}
	
	@Override
	public String toString() {
		return "*" + target.getId();
	}
	
	@Override
	public IReferenceType reference() {
		throw new UnsupportedOperationException();
	}
}
