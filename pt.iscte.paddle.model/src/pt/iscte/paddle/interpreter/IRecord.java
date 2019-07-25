package pt.iscte.paddle.interpreter;

import pt.iscte.paddle.model.IVariable;

public interface IRecord extends IValue {
	IReference getField(IVariable field);
	void setField(IVariable field, IValue value);
}
