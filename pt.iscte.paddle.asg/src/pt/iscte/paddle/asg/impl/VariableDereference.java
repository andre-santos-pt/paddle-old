package pt.iscte.paddle.asg.impl;

import java.util.List;

import pt.iscte.paddle.asg.IArrayElement;
import pt.iscte.paddle.asg.IArrayLength;
import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IProgramElement;
import pt.iscte.paddle.asg.IRecordFieldExpression;
import pt.iscte.paddle.asg.IRecordFieldVariable;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAddress;
import pt.iscte.paddle.asg.IVariableDereference;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IReference;
import pt.iscte.paddle.machine.IValue;

public class VariableDereference extends Expression implements IVariableDereference {
	private final IVariable variable;
	
	public VariableDereference(IVariable variable) {
		assert variable != null;
		this.variable = variable;
	}

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		IReference reference = stack.getTopFrame().getVariableStore(resolve());
		IVariable var = variable;
		while(var instanceof VariableDereference) {		
			var = ((VariableDereference) var).variable;
			reference = (IReference) reference.getTarget();
		}
		return reference;
	}

	@Override
	public IVariable getVariable() {
		return variable;
	}

	private IVariable resolve() {
		IVariable var = variable;
		while(var instanceof VariableDereference)
			var = ((VariableDereference) var).variable;
		return var;
	}
	
	@Override
	public String toString() {
		return getId();
	}

	@Override
	public IType getType() {
		return variable.getType();
	}

	@Override
	public IVariableAddress address() {
		return new VariableAddress(this);
	}

	@Override
	public IVariableDereference dereference() {
		return new VariableDereference(this);
	}

	@Override
	public IArrayLength length(List<IExpression> indexes) {
		return new ArrayLength(this, indexes);
	}

	@Override
	public IArrayElement element(List<IExpression> indexes) {
		return new ArrayElement(this, indexes);
	}

	@Override
	public IRecordFieldExpression field(IVariable field) {
		return new RecordFieldExpression(this, field); 
	}
	
	@Override
	public IRecordFieldVariable fieldVariable(IVariable field) {
		return new RecordFieldVariable(this, field);
	}

	@Override
	public IProgramElement getParent() {
		return variable.getParent();
	}

	@Override
	public String getId() {
		return variable.getId();
	}

	

}
