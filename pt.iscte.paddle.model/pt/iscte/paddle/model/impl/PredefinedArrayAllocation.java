package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IStackFrame;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IArrayType;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IPredefinedArrayAllocation;
import pt.iscte.paddle.model.IType;

public class PredefinedArrayAllocation extends Expression implements IPredefinedArrayAllocation {

	private final IArrayType type;
	private final List<IExpression> elements;

	private final boolean onHeap;
	
	PredefinedArrayAllocation(IArrayType arrayType, boolean onHeap, List<IExpression> elements) {
		for(IExpression e : elements)
			if(!e.getType().isSame(arrayType.getComponentTypeAt(1)))
				assert false : "all elements must have type equal to array component type";
		this.type = arrayType;
		this.elements = List.copyOf(elements);
		this.onHeap = onHeap;
	}


	@Override
	public boolean onHeapMemory() {
		return onHeap;
	}

	@Override
	public List<IExpression> getParts() {
		return elements;
	}

	@Override
	public IType getType() {
		return type.reference();
	}

	@Override
	public List<IExpression> getElements() {
		return elements;
	}

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		IStackFrame frame = stack.getTopFrame();
		IType baseType = type.getComponentTypeAt(1);
		int[] dims = new int[] { elements.size() };
		IArray array = onHeap ? 
				frame.getCallStack().getProgramState().allocateArray(baseType, dims) :
				frame.allocateArray(baseType, dims);
		
		for(int i = 0; i < values.size(); i++)
			array.setElement(i, values.get(i));
		return array;
	}
}
