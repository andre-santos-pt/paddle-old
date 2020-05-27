package pt.iscte.paddle.interpreter.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IEvaluable;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IArrayElement;
import pt.iscte.paddle.model.IArrayLength;
import pt.iscte.paddle.model.IConditionalExpression;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IProcedureDeclaration;
import pt.iscte.paddle.model.IRecordFieldExpression;
import pt.iscte.paddle.model.ISimpleExpression;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariableDeclaration;

class ProcedureReturnExpression implements ISimpleExpression, IEvaluable {
	final IProcedureDeclaration procedure;
	ProcedureReturnExpression(IProcedureDeclaration procedure) {
		this.procedure = procedure;
	}

	@Override
	public IType getType() {
		return procedure.getReturnType();
	}

	@Override
	public boolean includes(IVariableDeclaration variable) {
		return false;
	}
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		return stack.getLastTerminatedFrame().getReturn();
	}
	@Override
	public Object getProperty(Object key) {
		return null;
	}
	@Override
	public void setProperty(Object key, Object value) {
		
	}

	@Override
	public IConditionalExpression conditional(IExpression trueCase, IExpression falseCase) {
		assert false;
		return null;
	}

	@Override
	public IArrayLength length(List<IExpression> indexes) {
		assert false;
		return null;
	}

	@Override
	public IArrayElement element(List<IExpression> indexes) {
		assert false;
		return null;
	}

	@Override
	public IRecordFieldExpression field(IVariableDeclaration field) {
		assert false;
		return null;
	}
}