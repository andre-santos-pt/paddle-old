package pt.iscte.paddle.machine;

import pt.iscte.paddle.asg.IVariable;

public interface IRecord extends IValue {
	IValue getField(IVariable field);
	void setField(IVariable field, IValue value);
}
