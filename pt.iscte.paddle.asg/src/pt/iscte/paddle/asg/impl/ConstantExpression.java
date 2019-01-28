package pt.iscte.paddle.asg.impl;

import java.util.List;

import pt.iscte.paddle.asg.IConstant;
import pt.iscte.paddle.asg.IConstantExpression;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IValue;

class ConstantExpression extends Expression implements IConstantExpression {

	private final IConstant constant;

	public ConstantExpression(IConstant constant) {
		this.constant = constant;
	}
	
	@Override
	public IDataType getType() {
		return constant.getType();
	}

	@Override
	public IConstant getConstant() {
		return constant;
	}

	@Override
	public String toString() {
		return constant.getId();
	}
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) {
		return stack.getProgramState().getValue(getConstant().getValue().getStringValue());
	}
}
