package pt.iscte.paddle.machine.impl;

import java.util.List;

import pt.iscte.paddle.asg.IConditionalExpression;
import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IProcedureDeclaration;
import pt.iscte.paddle.asg.ISimpleExpression;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IEvaluable;
import pt.iscte.paddle.machine.IValue;

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
	public Object getProperty(String key) {
		return null;
	}
	@Override
	public void setProperty(String key, Object value) {
		
	}

	@Override
	public IConditionalExpression conditional(IExpression trueCase, IExpression falseCase) {
		assert false;
		return null;
	}
}