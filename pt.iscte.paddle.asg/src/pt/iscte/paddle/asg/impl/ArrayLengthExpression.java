package pt.iscte.paddle.asg.impl;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.asg.IArrayLengthExpression;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ExecutionError.Type;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IReference;
import pt.iscte.paddle.machine.IValue;

class ArrayLengthExpression extends Expression implements IArrayLengthExpression {
	private final IVariable variable;
	private final ImmutableList<IExpression> indexes;

	public ArrayLengthExpression(IVariable variable, List<IExpression> indexes) {
		this.variable = variable;
		this.indexes = ImmutableList.copyOf(indexes);
	}

	@Override
	public List<IExpression> getIndexes() {
		return indexes;
	}

	@Override
	public IVariable getVariable() {
		return variable;
	}

	@Override
	public IDataType getType() {
		return IDataType.INT;
	}

	@Override
	public String toString() {
		String text = getVariable().toString();
		for(IExpression e : indexes)
			text += "[" + e + "]";
		return text + ".length";
	}


	@Override
	public List<IExpression> decompose() {
		return indexes;
	}

	@Override
	public boolean isDecomposable() {
		return true;
	}	

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		assert values.size() == getIndexes().size();
		IReference ref = null;
		
		if(variable instanceof VariableReferenceValue)
			ref = (IReference) ((VariableReferenceValue) variable).evalutate(values, stack);
		else
			ref = stack.getTopFrame().getVariableStore(getVariable());
		
		IArray array = (IArray) ref.getTarget();
		IValue v = array;
		for(int i = 0; i < values.size(); i++) {
			int index = ((Number) values.get(i).getValue()).intValue();
			if(index < 0 || index >= ((IArray) v).getLength())
				throw new ExecutionError(Type.ARRAY_INDEX_BOUNDS, this, Integer.toString(index));
			v = ((IArray) v).getElement(index);
		}
		return stack.getTopFrame().getValue(((IArray) v).getLength());
	}
}