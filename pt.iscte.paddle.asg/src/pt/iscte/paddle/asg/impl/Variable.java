package pt.iscte.paddle.asg.impl;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.asg.IArrayElementAssignment;
import pt.iscte.paddle.asg.IArrayElementExpression;
import pt.iscte.paddle.asg.IArrayLengthExpression;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IProgramElement;
import pt.iscte.paddle.asg.IStructMemberAssignment;
import pt.iscte.paddle.asg.IStructMemberExpression;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAddress;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.asg.IVariableReferenceValue;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ExecutionError.Type;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IEvaluable;
import pt.iscte.paddle.machine.IReference;
import pt.iscte.paddle.machine.IStackFrame;
import pt.iscte.paddle.machine.IValue;

class Variable extends ProgramElement implements IVariable, IEvaluable {

	private final IProgramElement parent;
	private final String id;
	private final IDataType type;
	
	public Variable(IProgramElement parent, String name, IDataType type) {
		this.parent = parent;
		this.id = name;
		this.type = type;
	}

//	@Override
//	public boolean isPointer() {
//		return type instanceof IReferenceType;
//	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public IProgramElement getParent() {
		return parent;
	}
	
	@Override
	public IDataType getType() {
		return type;
	}

	@Override
	public String toString() {
		return id;
	}

	@Override
	public IVariableAssignment addAssignment(IExpression expression) {
		assert parent instanceof IBlock;
		return new VariableAssignment((IBlock) parent, this, expression);
	}
	
	@Override
	public IVariableAssignment addTargetAssignment(IExpression expression) {
		return VariableAssignment.onTarget((IBlock) parent, this, expression);
	}

//	@Override
//	public IVariableExpression expression() {
//		return new VariableExpression(this);
//	}
	
	@Override
	public IVariableAddress address() {
		return new VariableAddress(this);
	}
	
	@Override
	public IVariableReferenceValue valueOf() {
		return new VariableReferenceValue(this);
	}
	
	@Override
	public IStructMemberAssignment addMemberAssignment(String memberId, IExpression expression) {
		assert parent instanceof IBlock;
		return new StructMemberAssignment((IBlock) parent, this, memberId, expression);
	}
	
	@Override
	public IStructMemberExpression memberExpression(String memberId) {
		return new StructMemberExpression(this, memberId);
	}

	@Override
	public IArrayLengthExpression arrayLength(List<IExpression> indexes) {
		return new ArrayLengthExpression(indexes);
	}

	@Override
	public IArrayElementExpression arrayElement(List<IExpression> indexes) {
		return new ArrayElementExpression(this, indexes);
	}

	@Override
	public IArrayElementAssignment addArrayAssignment(IExpression expression, List<IExpression> indexes) {
		IProgramElement parent = getParent();
		assert parent instanceof IBlock;
		return new ArrayElementAssignment((IBlock) parent, this, indexes, expression);
	}
	

	
	private class ArrayLengthExpression extends Expression implements IArrayLengthExpression {
		private ImmutableList<IExpression> indexes;

		public ArrayLengthExpression(List<IExpression> indexes) {
			this.indexes = ImmutableList.copyOf(indexes);
		}

		@Override
		public List<IExpression> getIndexes() {
			return indexes;
		}

		@Override
		public IVariable getVariable() {
			return Variable.this;
		}

		@Override
		public IDataType getType() {
			return IDataType.INT;
		}

		@Override
		public String toString() {
			String text = getVariable().toString();
			for(IExpression e : indexes)
				text += "[" + e + "]";
			return text + ".length";
		}


		@Override
		public List<IExpression> decompose() {
			return indexes;
		}
		
		@Override
		public boolean isDecomposable() {
			return true;
		}	
		
		@Override
		public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
			assert values.size() == getIndexes().size();
			IReference ref = stack.getTopFrame().getVariableStore(getVariable().getId());
			IArray array = (IArray) ref.getTarget();
			IValue v = array;
			for(int i = 0; i < values.size(); i++) {
				int index = ((Number) values.get(i).getValue()).intValue();
				if(index < 0 || index >= ((IArray) v).getLength())
					throw new ExecutionError(Type.ARRAY_INDEX_BOUNDS, this, Integer.toString(index));
				v = ((IArray) v).getElement(index);
			}
			return stack.getTopFrame().getValue(((IArray) v).getLength());
		}
	}



	@Override
	public boolean isDecomposable() {
		return false;
	}

	@Override
	public List<IExpression> decompose() {
		return ImmutableList.of();
	}

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		IStackFrame topFrame = stack.getTopFrame();
		IValue val = topFrame.getVariableStore(getId()).getTarget();
		return val;
	}

//	@Override
//	public IVariable getVariable() {
//		return this;
//	}
}
