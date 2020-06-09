package pt.iscte.paddle.model.impl;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.interpreter.ArrayIndexError;
import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IReference;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IArrayElement;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IModel2CodeTranslator;

class ArrayElement extends Expression implements IArrayElement {
	private final IExpression target;
	private final ImmutableList<IExpression> parts;
	private final ImmutableList<IExpression> indexes;
	
	public ArrayElement(IExpression target, List<IExpression> indexes) {
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
	public String toString() {
		String text = getTarget().toString();
		for(IExpression e : indexes)
			text += "[" + e + "]";
		return text;
	}
	
	
	@Override
	public String translate(IModel2CodeTranslator t) {
		String text = getTarget().translate(t);
		for(IExpression e : indexes)
			text += "[" + e.translate(t) + "]";
		return text;
	}
	
	@Override
	public List<IExpression> getParts() {
		return parts;
	}
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		assert values.size() == getIndexes().size() + 1;
		IValue vTarget = values.get(0);
		if(vTarget instanceof IReference)
			vTarget = ((IReference) vTarget).getTarget();
		
		IExpression errorTarget = target;
		for(int i = 1; i < values.size(); i++) {
			IValue v = values.get(i);
			int index = ((Number) v.getValue()).intValue();
			if(index < 0 || index >= ((IArray) vTarget).getLength())
				throw new ArrayIndexError(this, index, errorTarget, indexes.get(i - 1), i - 1);
				
			vTarget = ((IArray) vTarget).getElement(index);
			errorTarget = errorTarget.element(indexes.get(i-1));
		}
		return vTarget;
	}

}
