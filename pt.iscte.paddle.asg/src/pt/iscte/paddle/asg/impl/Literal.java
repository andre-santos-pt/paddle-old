package pt.iscte.paddle.asg.impl;

import java.util.List;

import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.ILiteral;
import pt.iscte.paddle.asg.IValueType;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IValue;

public class Literal extends Expression implements ILiteral {

	private final IType type;
	private final String value;
	
	public Literal(IValueType type, String value) {
		assert value != null && !value.isEmpty();
		this.type = type == null ? IType.UNKNOWN : type;
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
	public String toString() {
		return value;
	}
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) {
		return stack.getProgramState().getValue(getStringValue());
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && getClass() == obj.getClass() && value.equals(((Literal) obj).value);
	}
	
	public static final ILiteral NULL = new Literal(null, "null");
}
