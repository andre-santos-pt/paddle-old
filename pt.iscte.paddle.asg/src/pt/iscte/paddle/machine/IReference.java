package pt.iscte.paddle.machine;

public interface IReference extends IValue {
	IValue getTarget();
	void setTarget(IValue r);
}
