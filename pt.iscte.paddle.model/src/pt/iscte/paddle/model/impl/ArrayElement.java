package pt.iscte.paddle.model.impl;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IReference;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IArrayElement;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IModel2CodeTranslator;
import pt.iscte.paddle.model.IVariable;

class ArrayElement extends Expression implements IArrayElement {
	private final IVariable variable;
	private final ImmutableList<IExpression> indexes;
	
	public ArrayElement(IVariable variable, List<IExpression> indexes) {
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
	public String translate(IModel2CodeTranslator t) {
		String text = getVariable().getId();
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
		if(variable instanceof VariableDereference)
			ref = (IReference) ((VariableDereference) variable).evalutate(values, stack);
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
