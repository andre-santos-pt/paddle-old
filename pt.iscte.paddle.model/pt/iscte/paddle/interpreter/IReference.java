package pt.iscte.paddle.interpreter;

public interface IReference extends IValue {
	IValue getTarget();
	
	void setTarget(IValue r);
	
	IReference copy();
	
	default boolean isNull() {
		return getTarget() == IValue.NULL;
	}
}
