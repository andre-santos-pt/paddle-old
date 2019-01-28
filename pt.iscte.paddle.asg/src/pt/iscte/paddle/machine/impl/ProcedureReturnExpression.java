package pt.iscte.paddle.machine.impl;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IProcedureDeclaration;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IEvaluable;
import pt.iscte.paddle.machine.IValue;

class ProcedureReturnExpression implements IExpression, IEvaluable {
	final IProcedureDeclaration procedure;
	ProcedureReturnExpression(IProcedureDeclaration procedure) {
		this.procedure = procedure;
	}

	@Override
	public List<IExpression> decompose() {
		return ImmutableList.of();
	}

	@Override
	public boolean isDecomposable() {
		return false;
	}

	@Override
	public IDataType getType() {
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
}