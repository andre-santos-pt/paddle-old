package pt.iscte.paddle.asg.impl;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.asg.IArrayElement;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IReference;
import pt.iscte.paddle.machine.IStackFrame;
import pt.iscte.paddle.machine.IValue;

class ArrayElementExpression extends Expression implements IArrayElement {
	private final IVariable variable;
	private final ImmutableList<IExpression> indexes;
	
	public ArrayElementExpression(IVariable variable, List<IExpression> indexes) {
		this.variable = variable;
		this.indexes = ImmutableList.copyOf(indexes);
	}
	
	@Override
	public List<IExpression> getIndexes() {
		return indexes;
	}
	
	@Override
	public String toString() {
		String text = getVariable().toString();
		for(IExpression e : indexes)
			text += "[" + e + "]";
		return text;
	}
	
	@Override
	public List<IExpression> decompose() {
		return indexes;
	}
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		assert values.size() == getIndexes().size();
		
		IReference ref = null;
		if(variable instanceof VariableReferenceValue)
			ref = (IReference) ((VariableReferenceValue) variable).evalutate(values, stack);
		else
			ref = stack.getTopFrame().getVariableStore(getVariable());
		
		IValue element = ref.getTarget();
		for(IValue v : values) {
			int index = ((Number) v.getValue()).intValue();
			if(index < 0)
				throw new ExecutionError(ExecutionError.Type.ARRAY_INDEX_BOUNDS, this, "negative array index", index);
			element = ((IArray) element).getElement(index);
		}
		return element;
	}

	@Override
	public IVariable getVariable() {
		return variable;
	}
	
}
