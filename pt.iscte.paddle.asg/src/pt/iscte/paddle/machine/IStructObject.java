package pt.iscte.paddle.machine;

public interface IStructObject extends IValue {

	IValue getField(String id);
	void setField(String id, IValue value);
}
