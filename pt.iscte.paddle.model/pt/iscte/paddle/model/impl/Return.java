package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IReturn;

class Return extends Statement implements IReturn {
	
	private final IExpression expression;
	private final boolean error;
	
	public Return(Block parent, int index) {
		this(parent, null, index, false);
	}
	
	public Return(Block parent, IExpression expression, int index, boolean error) {
		super(parent);
		this.expression = expression;
		this.error = error;
		addToParent(index);
	}

	@Override
	public IExpression getExpression() {
		return expression;
	}
	
	@Override
	public boolean isError() {
		return error;
	}
	
//	@Override
//	public String toString() {
//		return isVoid() ? "return" : "return " + expression;
//	}
	
	@Override
	public void execute(ICallStack stack, List<IValue> expressions) throws ExecutionError {
		if(expressions.size() == 1)
			stack.getTopFrame().setReturn(expressions.get(0));
	}
	
	@Override
	public String getExplanation(ICallStack stack, List<IValue> expressions) {
		String msg = "Terminates the execution of procedure " + getParent().getProcedure().getId();
		if(!isVoid())
			msg += " and returns value " + expressions.get(0);
		return msg;
	}
}
