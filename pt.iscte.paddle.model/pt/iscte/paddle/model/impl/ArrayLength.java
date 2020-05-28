package pt.iscte.paddle.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pt.iscte.paddle.interpreter.ArrayIndexError;
import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IReference;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.interpreter.NullPointerError;
import pt.iscte.paddle.model.IArrayLength;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IType;


class ArrayLength extends Expression implements IArrayLength {
	private final IExpression target;
	private final List<IExpression> parts;
	private final List<IExpression> indexes;

	public ArrayLength(IExpression target, List<IExpression> indexes) {
		this.target = target;
		this.parts = new ArrayList<>(1 + indexes.size());
		this.parts.add(target);
		this.parts.addAll(indexes);
		this.indexes = List.copyOf(indexes);
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
	public List<IExpression> getParts() {
		return Collections.unmodifiableList(parts);
	}

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		assert values.size() == getIndexes().size() + 1;
		IValue vTarget = values.get(0);
		if(vTarget instanceof IReference)
			vTarget = ((IReference) vTarget).getTarget();
		IArray array = (IArray) vTarget;
		
		if(array.isNull())
			throw new NullPointerError(target);
		
		IValue v = array;
		for(int i = 1; i < values.size(); i++) {
			int index = ((Number) values.get(i).getValue()).intValue();
			if(index < 0 || index >= ((IArray) v).getLength())
				throw new ArrayIndexError(this, index, target, indexes.get(i - 1), i - 1);
			v = ((IArray) v).getElement(index);
		}
		return stack.getTopFrame().getValue(((IArray) v).getLength());
	}
}