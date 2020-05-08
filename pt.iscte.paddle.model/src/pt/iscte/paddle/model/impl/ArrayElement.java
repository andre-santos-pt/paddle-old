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
import pt.iscte.paddle.model.IArrayElement;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IModel2CodeTranslator;

class ArrayElement extends Expression implements IArrayElement {
	private final IExpression target;
	private final List<IExpression> parts;
	private final List<IExpression> indexes;
	
	public ArrayElement(IExpression target, List<IExpression> indexes) {
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
		return Collections.unmodifiableList(parts);
	}
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		assert values.size() == getIndexes().size() + 1;
		IValue vTarget = values.get(0);
		if(vTarget instanceof IReference)
			vTarget = ((IReference) vTarget).getTarget();
		
		for(int i = 1; i < values.size(); i++) {
			IValue v = values.get(i);
			int index = ((Number) v.getValue()).intValue();
			if(index < 0 || index >= ((IArray) vTarget).getLength())
				throw new ArrayIndexError(this, index, target, indexes.get(i - 1), i - 1);
				
			vTarget = ((IArray) vTarget).getElement(index);
		}
		return vTarget;
	}

}
