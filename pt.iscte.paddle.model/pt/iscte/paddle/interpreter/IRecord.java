package pt.iscte.paddle.interpreter;

import pt.iscte.paddle.model.IVariableDeclaration;

public interface IRecord extends IValue {
	IReference getField(IVariableDeclaration field);
	void setField(IVariableDeclaration field, IValue value);
}
