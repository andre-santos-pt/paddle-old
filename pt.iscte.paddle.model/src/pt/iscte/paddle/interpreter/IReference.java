package pt.iscte.paddle.interpreter;

public interface IReference extends IValue {
	IValue getTarget();
	
	void setTarget(IValue r);
	
}
