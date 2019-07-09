package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IReturn;

class Return extends Statement implements IReturn {

	private final IExpression expression;
	
	public Return(Block parent) {
		this(parent, null);
	}
	
	public Return(Block parent, IExpression expression) {
		super(parent, true);
		this.expression = expression;
	}

	@Override
	public IExpression getExpression() {
		return expression;
	}
	
	@Override
	public String toString() {
		return "return " + expression;
	}
	
	@Override
	public void execute(ICallStack stack, List<IValue> expressions) throws ExecutionError {
		if(expressions.size() == 1)
			stack.getTopFrame().setReturn(expressions.get(0));
	}
}
