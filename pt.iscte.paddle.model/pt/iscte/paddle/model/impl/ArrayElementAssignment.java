package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ArrayIndexError;
import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IRecord;
import pt.iscte.paddle.interpreter.IReference;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.interpreter.NullPointerError;
import pt.iscte.paddle.model.IArrayAccess;
import pt.iscte.paddle.model.IArrayElementAssignment;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ITargetExpression;

class ArrayElementAssignment extends Statement implements IArrayElementAssignment {
//	private final ITargetExpression target;
//	private final List<IExpression> indexes;
	private final IArrayAccess access; 
	private final IExpression expression;
	
	public ArrayElementAssignment(IBlock parent, ITargetExpression target, IExpression expression, int index, List<IExpression> indexes) {
		super(parent);
//		this.target = target;
//		this.indexes = List.copyOf(indexes);
		access = new ArrayElement(target, indexes);
		this.expression = expression;
		addToParent(index);
	}
	
	@Override
	public IArrayAccess getArrayAccess() {
		return access;
	}

//	@Override
//	public ITargetExpression getTarget() {
//		return target;
//	}
//	
//	@Override
//	public List<IExpression> getIndexes() {
//		return indexes;
//	}
	

	@Override
	public IExpression getExpression() {
		return expression;
	}
	
//	@Override
//	public String toString() {
//		String text = access.getTarget().getId();
//		for(IExpression e : access.getIndexes())
//			text += "[" + e + "]";
//		
//		text += " = " + getExpression();
//		return text;
//	}
	
	@Override
	public void execute(ICallStack stack, List<IValue> values) throws ExecutionError {
		assert values.size() == access.getIndexes().size() + 1;
		
		IReference ref = null;
		if(access.getTarget() instanceof VariableDereference)
			ref = (IReference) ((VariableDereference) access.getTarget()).evalutate(values, stack);
		else if(access.getTarget() instanceof RecordFieldExpression) {
			RecordFieldExpression rexp = (RecordFieldExpression) access.getTarget();
			IRecord r = rexp.resolveTarget(stack);
			ref = (IReference) r.getField(rexp.getField());
		}
		else if(access.getTarget() instanceof VariableExpression)
			ref = stack.getTopFrame().getVariableStore(((VariableExpression) access.getTarget()).getVariable());
		else
			assert false;
		
		IValue valueArray = ref.getTarget();
		if(valueArray.isNull())
			throw new NullPointerError(access.getTarget());
		
		IArray array = (IArray) valueArray;
		
		List<IExpression> indexes = access.getIndexes();
		IValue v = array;
		for(int i = 0; i < indexes.size()-1; i++) {
			int index = ((Number) values.get(i).getValue()).intValue();
			if(index < 0 || index >= ((IArray)v).getLength()) {
				throw new ArrayIndexError(access, index, indexes.get(i), i);
			}
			v = array.getElement(index);
		}
		
		int index = ((Number) values.get(indexes.size()-1).getValue()).intValue();
		if(index < 0 || index >= ((IArray)v).getLength())			
			throw new ArrayIndexError(access, index, indexes.get(indexes.size()-1), indexes.size()-1);

		
		IValue val = values.get(values.size()-1);
		((IArray) v).setElement(index, val);
	}
}
