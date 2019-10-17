package pt.iscte.paddle.model.impl;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.interpreter.ArrayIndexError;
import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IRecord;
import pt.iscte.paddle.interpreter.IReference;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IArrayElement;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IModel2CodeTranslator;

class ArrayElement extends Expression implements IArrayElement {
	private final IExpression target;
	private final ImmutableList<IExpression> indexes;
	
	public ArrayElement(IExpression target, List<IExpression> indexes) {
		this.target = target;
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
		String text = getTarget().getId();
		for(IExpression e : indexes)
			text += "[" + e.translate(t) + "]";
		return text;
	}
	
	@Override
	public List<IExpression> getParts() {
		return indexes;
	}
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		assert values.size() == getIndexes().size();
		
		IReference ref = null;
		if(target instanceof VariableDereference)
			ref = (IReference) ((VariableDereference) target).evalutate(values, stack);
		else if(target instanceof RecordFieldExpression) {
			RecordFieldExpression rexp = (RecordFieldExpression) target;
			IRecord r = rexp.resolveTarget(stack);
			ref = (IReference) r.getField(rexp.getField());
		}
		else if(target instanceof Variable)
			ref = stack.getTopFrame().getVariableStore((Variable) target);
		else
			assert false;
		
		IValue element = ref.getTarget();
		for(int i = 0; i < values.size(); i++) {
			IValue v = values.get(i);
			int index = ((Number) v.getValue()).intValue();
			if(index < 0 || index >= ((IArray)element).getLength())
				throw new ArrayIndexError(this, index, target, indexes.get(i), index);
//				throw new ExecutionError(ExecutionError.Type.ARRAY_INDEX_BOUNDS, this, "invalid index", index);
				
			element = ((IArray) element).getElement(index);
		}
		return element;
	}

}
