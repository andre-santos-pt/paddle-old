package pt.iscte.paddle.asg.impl;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.asg.IArrayElementExpression;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IStackFrame;
import pt.iscte.paddle.machine.IValue;

class ArrayElementExpression extends VariableExpression implements IArrayElementExpression {
	private ImmutableList<IExpression> indexes;
	
	public ArrayElementExpression(IVariable variable, List<IExpression> indexes) {
		super(variable);
		this.indexes = ImmutableList.copyOf(indexes);
	}
	
//	@Override
//	public IArrayVariable getVariable() {
//		return (IArrayVariable) super.getVariable();
//	}

	@Override
	public List<IExpression> getIndexes() {
		return indexes;
	}
	
//	@Override
//	public IDataType getType() {
//		return getVariable().getComponentType();
//	}
	
	@Override
	public String toString() {
		String text = getVariable().getId();
		for(IExpression e : indexes)
			text += "[" + e + "]";
		return text;
	}
	
	@Override
	public List<IExpression> decompose() {
		return indexes;
	}
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		assert values.size() == getIndexes().size();
		IStackFrame frame = stack.getTopFrame();
		IValue variable = frame.getVariableStore(getVariable().getId()).getTarget();
		IValue element = variable;
		for(IValue v : values) {
			int index = ((Number) v.getValue()).intValue();
			if(index < 0)
				throw new ExecutionError(ExecutionError.Type.NEGATIVE_ARRAY_SIZE, this, "negative array index", index);
			element = ((IArray) element).getElement(index);
		}
		return element;
	}
	
}
