package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ExecutionError.Type;
import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IStackFrame;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IArrayAllocation;
import pt.iscte.paddle.model.IArrayType;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IType;

class ArrayAllocation extends Expression implements IArrayAllocation {
	private final IArrayType type;
	private final List<IExpression> dimensions;

	private final boolean onHeap;
	
	private ArrayAllocation(IArrayType arrayType, List<IExpression> dimensions, boolean onHeap) {
		this.type = arrayType;
		this.dimensions = List.copyOf(dimensions);
		this.onHeap = onHeap;
	}

	public static ArrayAllocation heap(IArrayType arrayType, List<IExpression> dimensions) {
		return new ArrayAllocation(arrayType, dimensions, true);
	}
	
	public static ArrayAllocation stack(IArrayType arrayType, List<IExpression> dimensions) {
		return new ArrayAllocation(arrayType, dimensions, false);
	}
	
	@Override
	public IType getType() {
		return onHeap ? type.reference() : type;
	}

	@Override
	public boolean onHeapMemory() {
		return onHeap;
	}
	
	@Override
	public List<IExpression> getDimensions() {
		return dimensions;
	}

	@Override
	public List<IExpression> getParts() {
		return dimensions;
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

		IType baseType = type.getComponentTypeAt(dims.length);
		IArray array = onHeap ? 
				frame.getCallStack().getProgramState().allocateArray(baseType, dims) :
				frame.allocateArray(baseType, dims);
		return array;
	}
}
