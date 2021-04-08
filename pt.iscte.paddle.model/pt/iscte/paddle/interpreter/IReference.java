package pt.iscte.paddle.interpreter;

import pt.iscte.paddle.interpreter.impl.Reference;

public interface IReference extends IValue {
	IValue getTarget();
	
	void setTarget(IValue r);
	
	IReference copy();
	
	default boolean isNull() {
		return getTarget() == IValue.NULL;
	}
	
	public static IReference create(IValue value) {
		return new Reference(value);
	}
}
