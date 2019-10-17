package pt.iscte.paddle.model.impl;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.interpreter.ArrayIndexError;
import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ExecutionError.Type;
import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IReference;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.interpreter.NullPointerError;
import pt.iscte.paddle.model.IArrayLength;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariable;

class ArrayLength extends Expression implements IArrayLength {
	private final IVariable variable;
	private final ImmutableList<IExpression> indexes;

	// TODO target as expression
	public ArrayLength(IVariable variable, List<IExpression> indexes) {
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
	public IType getType() {
		return IType.INT;
	}

	@Override
	public String toString() {
		String text = getVariable().toString();
		for(IExpression e : indexes)
			text += "[" + e + "]";
		return text + ".length";
	}


	@Override
	public List<IExpression> getParts() {
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
		
		if(variable instanceof VariableDereference)
			ref = (IReference) ((VariableDereference) variable).evalutate(values, stack);
		else
			ref = stack.getTopFrame().getVariableStore(getVariable());
		
		IArray array = (IArray) ref.getTarget();
		
		if(array.isNull())
			throw new NullPointerError(variable);
		
		IValue v = array;
		for(int i = 0; i < values.size(); i++) {
			int index = ((Number) values.get(i).getValue()).intValue();
			if(index < 0 || index >= ((IArray) v).getLength())
				throw new ArrayIndexError(this, index, variable, indexes.get(i), i);
			v = ((IArray) v).getElement(index);
		}
		return stack.getTopFrame().getValue(((IArray) v).getLength());
	}
}