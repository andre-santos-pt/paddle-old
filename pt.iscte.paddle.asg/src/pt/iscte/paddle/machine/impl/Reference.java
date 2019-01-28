package pt.iscte.paddle.machine.impl;

import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.machine.IReference;
import pt.iscte.paddle.machine.IValue;

public class Reference implements IReference {
	private IValue target;
	//	private IReferenceType type;

	public Reference(IValue target) {
		//		this.type = type;
		this.target = target;
	}

	@Override
	public IDataType getType() {
		return target == IValue.NULL ? null : target.getType();
	}

	@Override
	public Object getValue() {
		return target == IValue.NULL ? null : target;
	}

	@Override
	public IValue getTarget() {
		return target;
	}

	@Override
	public void setTarget(IValue value) {
		target = value;
	}

	@Override
	public void setValue(Object o) {

		target.setValue(o);
	}

	@Override
	public IValue copy() {
		return new Reference(target);
	}

	@Override
	public String toString() {
		return "->" + getTarget();
	}
}
