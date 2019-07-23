package pt.iscte.paddle.interpreter.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IEvaluable;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IConditionalExpression;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IProcedureDeclaration;
import pt.iscte.paddle.model.ISimpleExpression;
import pt.iscte.paddle.model.IType;

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
}