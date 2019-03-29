package pt.iscte.paddle.asg.impl;

import java.util.List;

import pt.iscte.paddle.asg.IArrayElementExpression;
import pt.iscte.paddle.asg.IArrayLengthExpression;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IProgramElement;
import pt.iscte.paddle.asg.IRecordFieldExpression;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAddress;
import pt.iscte.paddle.asg.IVariableReferenceValue;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IReference;
import pt.iscte.paddle.machine.IValue;

public class VariableReferenceValue extends Expression implements IVariableReferenceValue {
	private final IVariable variable;
	
	public VariableReferenceValue(IVariable variable) {
		assert variable != null;
		this.variable = variable;
	}

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		IReference reference = stack.getTopFrame().getVariableStore(resolve());
		IVariable var = variable;
		while(var instanceof VariableReferenceValue) {		
			var = ((VariableReferenceValue) var).variable;
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
		while(var instanceof VariableReferenceValue)
			var = ((VariableReferenceValue) var).variable;
		return var;
	}
	
	@Override
	public String toString() {
		return getId();
	}

	@Override
	public IDataType getType() {
		return variable.getType();
	}

	@Override
	public IVariableAddress address() {
		return new VariableAddress(this);
	}

	@Override
	public IVariableReferenceValue valueOf() {
		return new VariableReferenceValue(this);
	}

	@Override
	public IArrayLengthExpression arrayLength(List<IExpression> indexes) {
		return new ArrayLengthExpression(this, indexes);
	}

	@Override
	public IArrayElementExpression arrayElement(List<IExpression> indexes) {
		return new ArrayElementExpression(this, indexes);
	}

	@Override
	public IRecordFieldExpression field(IVariable field) {
		return new RecordFieldExpression(this, field); 
	}

	@Override
	public IProgramElement getParent() {
		return variable.getParent();
	}

	@Override
	public String getId() {
		return "*" + variable.getId();
	}

}
