package pt.iscte.paddle.interpreter.impl;

import pt.iscte.paddle.interpreter.IReference;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IType;

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
		return getTarget();
//		return target == IValue.NULL ? null : target;
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
	public IReference copy() {
		return new Reference(target);
	}

	@Override
	public String toString() {
		return "->" + getTarget();
	}
}
