package pt.iscte.paddle.asg.impl;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.asg.IArrayElementAssignment;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IStackFrame;
import pt.iscte.paddle.machine.IValue;

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
	
//	@Override
//	public IArrayVariable getVariable() {
//		return (IArrayVariable) super.getVariable();
//	}

	@Override
	public String toString() {
		String text = getVariable().getId();
		for(IExpression e : indexes)
			text += "[" + e + "]";
		
		text += " = " + getExpression();
		return text;
	}
	
	@Override
	public boolean execute(ICallStack callStack, List<IValue> values) throws ExecutionError {
		assert values.size() == getIndexes().size() + 1;
		IStackFrame frame = callStack.getTopFrame();
		IValue valueArray = frame.getVariableStore(getVariable().getId()).getTarget();
		if(valueArray.isNull())
			throw new ExecutionError(ExecutionError.Type.NULL_POINTER, this, "null pointer", getVariable());
		
		IArray array = (IArray) valueArray;
		
		List<IExpression> indexes = getIndexes();
		IValue v = array;
		for(int i = 0; i < indexes.size()-1; i++) {
			int index = ((Number) values.get(i).getValue()).intValue();
			v = array.getElement(index);
		}
		int index = ((Number) values.get(indexes.size()-1).getValue()).intValue();
		IValue val = values.get(values.size()-1);
		((IArray) v).setElement(index, val);
		return true;
	}
}
