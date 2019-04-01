package pt.iscte.paddle.asg.impl;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.asg.IArrayAllocation;
import pt.iscte.paddle.asg.IArrayType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ExecutionError.Type;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IStackFrame;
import pt.iscte.paddle.machine.IValue;

class ArrayAllocation extends Expression implements IArrayAllocation {
	private final IArrayType type;
	private final ImmutableList<IExpression> dimensions;

	public ArrayAllocation(IArrayType arrayType, List<IExpression> dimensions) {
		this.type = arrayType;
		this.dimensions = ImmutableList.copyOf(dimensions);
	}

	@Override
	public IArrayType getType() {
		return type;
	}

	@Override
	public IArrayType getArrayType() {
		return type;
	}

	@Override
	public List<IExpression> getDimensions() {
		return dimensions;
	}

	@Override
	public String toString() {
		String text = "new " + type.getComponentTypeAt(dimensions.size());
		for(IExpression e : dimensions)
			text += "[" + e + "]";
		return text;
	}

	@Override
	public List<IExpression> decompose() {
		return dimensions;
	}

	@Override
	public boolean isDecomposable() {
		return true;
	}	


	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		assert values.size() > 0 && values.size() <= type.getDimensions();
		IStackFrame frame = stack.getTopFrame();

		int[] dims = new int[type.getDimensions()];
		for(int i = 0; i < dims.length; i++) {
			if(i < values.size()) {
				dims[i] = ((Number) values.get(i).getValue()).intValue();
				if(dims[i] < 0)
					throw new ExecutionError(Type.NEGATIVE_ARRAY_SIZE, this, Integer.toString(dims[i]));
			}
			else
				dims[i] = -1;
		}

		IArray array = frame.allocateArray(getType().getComponentTypeAt(dims.length), dims); 
		return array;
	}
}
