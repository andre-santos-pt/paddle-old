package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IValueType;

public class Literal extends Expression implements ILiteral {

	private final IType type;
	private final String value;
	
	public Literal(IValueType type, String value) {
		assert value != null && !value.isEmpty();
		this.type = type == null ? IType.UNBOUND : type;
		this.value = value;
	}
	
	@Override
	public IType getType() {
		return type;
	}
	
	@Override
	public String getStringValue() {
		return value;
	}
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) {
		return stack.getProgramState().getValue(getStringValue());
	}

	public static final ILiteral NULL = new Literal(null, "null");
}
