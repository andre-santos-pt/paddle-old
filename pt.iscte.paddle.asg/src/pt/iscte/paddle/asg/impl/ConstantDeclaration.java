package pt.iscte.paddle.asg.impl;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.asg.IConstant;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.ILiteral;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IValue;

class ConstantDeclaration extends Expression implements IConstant {
	private final IModule program;
	private final IDataType type;
	private final ILiteral value;
	
	public ConstantDeclaration(IModule program, IDataType type, ILiteral value) {
		this.program = program;
		this.type = type;
		this.value = value;
	}

	@Override
	public IModule getProgram() {
		return program;
	}

	@Override
	public IDataType getType() {
		return type;
	}

	@Override
	public ILiteral getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return type + " " + getId() + " = " + value;
	}

	@Override
	public List<IExpression> decompose() {
		return ImmutableList.of();
	}

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) {
		return stack.getProgramState().getValue(value.getStringValue());
	}

}
