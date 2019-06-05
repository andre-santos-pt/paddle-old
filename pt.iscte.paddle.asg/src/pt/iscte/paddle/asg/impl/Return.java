package pt.iscte.paddle.asg.impl;

import java.util.List;

import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IValue;

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
