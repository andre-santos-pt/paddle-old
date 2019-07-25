package pt.iscte.paddle.model.impl;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IRecord;
import pt.iscte.paddle.interpreter.IReference;
import pt.iscte.paddle.interpreter.IStackFrame;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IArrayElementAssignment;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IRecordFieldVariable;
import pt.iscte.paddle.model.IVariable;

class ArrayElementAssignment extends VariableAssignment implements IArrayElementAssignment {

	private ImmutableList<IExpression> indexes;

	public ArrayElementAssignment(IBlock parent, IVariable variable, List<IExpression> indexes, IExpression expression) {
		super(parent, variable, expression);
		this.indexes = ImmutableList.copyOf(indexes);
	}

	@Override
	public List<IExpression> getIndexes() {
		return indexes;
	}
	
	@Override
	public String toString() {
		String text = getVariable().getId();
		for(IExpression e : indexes)
			text += "[" + e + "]";
		
		text += " = " + getExpression();
		return text;
	}
	
	@Override
	public void execute(ICallStack callStack, List<IValue> values) throws ExecutionError {
		assert values.size() == getIndexes().size() + 1;
		
		IStackFrame frame = callStack.getTopFrame();

		// TODO record resolve
		
		IVariable var = getVariable();
		IReference ref = null;
		if(var instanceof VariableDereference)
			ref = (IReference) ((VariableDereference) var).evalutate(values, callStack);
		else if(var instanceof IRecordFieldVariable) {
				IVariable parent = (IVariable) var.getParent();
				ref = frame.getVariableStore(parent);
				IRecord rec = (IRecord) ref.getTarget();
				ref = (IReference) rec.getField(((IRecordFieldVariable) var).getField());
		}
		else
			ref = frame.getVariableStore(var);
		
		IValue valueArray = ref.getTarget();
		if(valueArray.isNull())
			throw new ExecutionError(ExecutionError.Type.NULL_POINTER, this, "null pointer", var);
		
		IArray array = (IArray) valueArray;
		
		List<IExpression> indexes = getIndexes();
		IValue v = array;
		for(int i = 0; i < indexes.size()-1; i++) {
			int index = ((Number) values.get(i).getValue()).intValue();
			if(index < 0 || index >= ((IArray)v).getLength())
				throw new ExecutionError(ExecutionError.Type.ARRAY_INDEX_BOUNDS, this, "invalid index", index);
			v = array.getElement(index);
		}
		
		int index = ((Number) values.get(indexes.size()-1).getValue()).intValue();
		if(index < 0 || index >= ((IArray)v).getLength())
			throw new ExecutionError(ExecutionError.Type.ARRAY_INDEX_BOUNDS, this, "invalid index", index);
		
		IValue val = values.get(values.size()-1);
		((IArray) v).setElement(index, val);
	}
}
