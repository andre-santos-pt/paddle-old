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
import pt.iscte.paddle.interpreter.NullPointerError;
import pt.iscte.paddle.model.IArrayElementAssignment;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;

class ArrayElementAssignment extends Statement implements IArrayElementAssignment {
	private final IExpression target;
	private final ImmutableList<IExpression> indexes;
	private final IExpression expression;
	
	public ArrayElementAssignment(IBlock parent, IExpression target, IExpression expression, int index, List<IExpression> indexes) {
		super(parent);
		this.target = target;
		this.indexes = ImmutableList.copyOf(indexes);
		this.expression = expression;
		addToParent(index);
	}

	@Override
	public IExpression getTarget() {
		return target;
	}
	
	@Override
	public List<IExpression> getIndexes() {
		return indexes;
	}
	

	@Override
	public IExpression getExpression() {
		return expression;
	}
	
	@Override
	public String toString() {
		String text = getTarget().getId();
		for(IExpression e : indexes)
			text += "[" + e + "]";
		
		text += " = " + getExpression();
		return text;
	}
	
	@Override
	public void execute(ICallStack stack, List<IValue> values) throws ExecutionError {
		assert values.size() == getIndexes().size() + 1;
		
		IReference ref = null;
		if(target instanceof VariableDereference)
			ref = (IReference) ((VariableDereference) target).evalutate(values, stack);
		else if(target instanceof RecordFieldExpression) {
			RecordFieldExpression rexp = (RecordFieldExpression) target;
			IRecord r = rexp.resolveTarget(stack);
			ref = (IReference) r.getField(rexp.getField());
		}
		else if(target instanceof VariableExpression)
			ref = stack.getTopFrame().getVariableStore(((VariableExpression) target).getVariable());
		else
			assert false;
		
		IValue valueArray = ref.getTarget();
		if(valueArray.isNull())
			throw new NullPointerError(target);
		
		IArray array = (IArray) valueArray;
		
		List<IExpression> indexes = getIndexes();
		IValue v = array;
		for(int i = 0; i < indexes.size()-1; i++) {
			int index = ((Number) values.get(i).getValue()).intValue();
			if(index < 0 || index >= ((IArray)v).getLength()) {
				throw new ArrayIndexError(this, index, target, indexes.get(i), i);
			}
			v = array.getElement(index);
		}
		
		int index = ((Number) values.get(indexes.size()-1).getValue()).intValue();
		if(index < 0 || index >= ((IArray)v).getLength())			
			throw new ArrayIndexError(this, index, target, indexes.get(indexes.size()-1), indexes.size()-1);

		
		IValue val = values.get(values.size()-1);
		((IArray) v).setElement(index, val);
	}
}
