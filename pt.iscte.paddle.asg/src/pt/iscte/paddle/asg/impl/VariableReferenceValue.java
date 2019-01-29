package pt.iscte.paddle.asg.impl;

import java.util.List;

import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableReferenceValue;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IReference;
import pt.iscte.paddle.machine.IValue;

public class VariableReferenceValue extends Variable implements IVariableReferenceValue {

	public VariableReferenceValue(IVariable variable) {
		super(variable.getParent(), variable.getId(), variable.getType());
	}

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		IReference reference = stack.getTopFrame().getVariableStore(getId());
		return reference.getTarget();
	}

//	@Override
//	public IVariableExpression getVariableExpression() {
//		return new VariableExpression(this);
//	}
	
	@Override
	public String toString() {
		return "*" + super.toString();
	}

}
