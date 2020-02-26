package pt.iscte.paddle.model.impl;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.interpreter.ArrayIndexError;
import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.interpreter.NullPointerError;
import pt.iscte.paddle.model.IArrayLength;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IType;

class ArrayLength extends Expression implements IArrayLength {
	private final IExpression target;
	private final ImmutableList<IExpression> parts;
	private final ImmutableList<IExpression> indexes;

	public ArrayLength(IExpression target, List<IExpression> indexes) {
		this.target = target;
		this.parts = ImmutableList.<IExpression>builder().add(target).addAll(indexes).build();
		this.indexes = ImmutableList.copyOf(indexes);
	}

	@Override
	public List<IExpression> getIndexes() {
		return indexes;
	}

	@Override
	public IExpression getTarget() {
		return target;
	}

	@Override
	public IType getType() {
		return IType.INT;
	}

	@Override
	public String toString() {
		String text = getTarget().toString();
		for(IExpression e : indexes)
			text += "[" + e + "]";
		return text + ".length";
	}


	@Override
	public List<IExpression> getParts() {
		return parts;
	}

	@Override
	public boolean isDecomposable() {
		return true;
	}	

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		assert values.size() == getIndexes().size() + 1;
//		IReference ref = null;
		
//		if(target instanceof VariableDereference)
//			ref = (IReference) ((VariableDereference) target).evalutate(values, stack);
//		else
//			ref = stack.getTopFrame().getVariableStore(getTarget());
		
//		IArray array = (IArray) ref.getTarget();
		IArray array = (IArray) values.get(0);
		
		if(array.isNull())
			throw new NullPointerError(target);
		
		IValue v = array;
		for(int i = 0; i < values.size(); i++) {
			int index = ((Number) values.get(i).getValue()).intValue();
			if(index < 0 || index >= ((IArray) v).getLength())
				throw new ArrayIndexError(this, index, target, indexes.get(i), i);
			v = ((IArray) v).getElement(index);
		}
		return stack.getTopFrame().getValue(((IArray) v).getLength());
	}
}