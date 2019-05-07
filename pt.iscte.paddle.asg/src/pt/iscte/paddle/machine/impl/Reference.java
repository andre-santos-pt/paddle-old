package pt.iscte.paddle.machine.impl;

import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.machine.IReference;
import pt.iscte.paddle.machine.IValue;

public class Reference implements IReference {
	private IValue target;

	public Reference(IValue target) {
		this.target = target;
	}

	@Override
	public IType getType() {
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

//	@Override
//	public void setValue(IValue v) {
//		target 
//	}

	@Override
	public IReference copy() {
		return new Reference(target);
	}

	@Override
	public String toString() {
		return "->" + getTarget();
	}
}
