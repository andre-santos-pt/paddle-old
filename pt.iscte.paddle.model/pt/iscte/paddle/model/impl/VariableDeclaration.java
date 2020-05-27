package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IExecutable;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IArrayElement;
import pt.iscte.paddle.model.IArrayLength;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IRecordFieldExpression;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariableAddress;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.IVariableDereference;
import pt.iscte.paddle.model.IVariableExpression;

class VariableDeclaration extends ProgramElement implements IVariableDeclaration, IExecutable {

	private final IProgramElement parent;
	private final IType type;

	public VariableDeclaration(IProgramElement parent, IType type, String...flags) {
		super(flags);
		this.parent = parent;
		this.type = type;
	}

	@Override
	public IProgramElement getParent() {
		return parent;
	}

	@Override
	public IType getType() {
		return type;
	}

	@Override
	public String toString() {
		return getId();
	}
	
	@Override
	public IVariableAddress address() {
		return new VariableAddress(this.expression());
	}

	@Override
	public IVariableDereference dereference() {
		return new VariableDereference(this.expression());
	}

	@Override
	public IRecordFieldExpression field(IVariableDeclaration field) {
		return new RecordFieldExpression(this.expression(), field);
	}
	
	@Override
	public IArrayLength length(List<IExpression> indexes) {
		return new ArrayLength(this.expression(), indexes);
	}

	@Override
	public IArrayElement element(List<IExpression> indexes) {
		return new ArrayElement(this.expression(), indexes);
	}

	@Override
	public IVariableExpression expression() {
		return new VariableExpression(this);
	}
	
	@Override
	public void execute(ICallStack stack, List<IValue> expressions) throws ExecutionError {

	}

}
