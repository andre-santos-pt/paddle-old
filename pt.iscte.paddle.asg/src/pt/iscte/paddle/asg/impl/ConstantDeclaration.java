package pt.iscte.paddle.asg.impl;

import pt.iscte.paddle.asg.IConstant;
import pt.iscte.paddle.asg.IConstantExpression;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.ILiteral;
import pt.iscte.paddle.asg.IModule;

class ConstantDeclaration extends ProgramElement implements IConstant {
	private final IModule program;
	private final String id;
	private final IDataType type;
	private final ILiteral value;
	
	public ConstantDeclaration(IModule program, String id, IDataType type, ILiteral value) {
		this.program = program;
		this.id = id;
		this.type = type;
		this.value = value;
	}

	@Override
	public IModule getProgram() {
		return program;
	}

	@Override
	public String getId() {
		return id;
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
	public IConstantExpression expression() {
		return new ConstantExpression(this);
	}
	
	@Override
	public String toString() {
		return type + " " + id + " = " + value;
	}

}
