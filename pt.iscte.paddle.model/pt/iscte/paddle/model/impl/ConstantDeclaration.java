package pt.iscte.paddle.model.impl;

import pt.iscte.paddle.model.IConstantDeclaration;
import pt.iscte.paddle.model.IConstantExpression;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IType;

class ConstantDeclaration extends ProgramElement implements IConstantDeclaration {
	private final Module program;
	private final IType type;
	private IExpression value;
	
	public ConstantDeclaration(Module program, IType type, IExpression value) {
		this.program = program;
		this.type = type;
		this.value = value;
	}

	@Override
	public IModule getProgram() {
		return program;
	}

	@Override
	public IType getType() {
		return type;
	}

	@Override
	public IExpression getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return getId();
	}


	@Override
	public void setValue(IExpression value) {
		assert value != null;
		this.value = value;
		setProperty("VALUE", value);
		
//		program.executeCommand(new PropertyModifyCommand<IConstant>(this, "VALUE", value));
	}
	
	@Override
	public IConstantExpression expression() {
		return new ConstantExpression(this);
	}
	
}
