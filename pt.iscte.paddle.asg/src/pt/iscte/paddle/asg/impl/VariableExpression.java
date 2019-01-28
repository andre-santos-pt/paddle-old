package pt.iscte.paddle.asg.impl;

import java.util.List;

import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableExpression;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IStackFrame;
import pt.iscte.paddle.machine.IValue;

class VariableExpression extends Expression implements IVariableExpression {
//	private enum Type {
//		ADDRESS("&"), VALUE("*"), NONE("");
//		String symbol;
//		private Type(String symbol) {
//			this.symbol = symbol;
//		}
//		
//		@Override
//		public String toString() {
//			return symbol;
//		}
//	}
	
	private final IVariable variable;
//	private final Type type;
	
//	private VariableExpression(IVariable variable, Type type) {
//		assert variable != null;
//		this.variable = variable;
//		this.type = type;
//	}

	public VariableExpression(IVariable variable) {
		this.variable = variable;
	}
			
//	public static VariableExpression address(IVariable variable) {
//		return new VariableExpression(variable, Type.ADDRESS);
//	}
	
//	public static VariableExpression value(IVariable variable) {
//		return new VariableExpression(variable, Type.VALUE);
//	}
	
	
	@Override
	public IVariable getVariable() {
		return variable;
	}

	@Override
	public String toString() {
		return variable.getId();
	}
	
	
//	@Override
//	public boolean equals(Object obj) {
//		return obj != null && getClass() == obj.getClass() && variable.equals(obj);
//	}
	
//	@Override
//	public int hashCode() {
//		return variable.hashCode();
//	}

//	@Override
//	public boolean isAddress() {
//		return type == Type.ADDRESS;
//	}

//	@Override
//	public boolean isValue() {
//		return type == Type.VALUE;
//	}
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		String varId = getVariable().getId();
		IStackFrame topFrame = stack.getTopFrame();
		IValue val = topFrame.getVariableStore(varId).getTarget();
		return val;
	}
}
