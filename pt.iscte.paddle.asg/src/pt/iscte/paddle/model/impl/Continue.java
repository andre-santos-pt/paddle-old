package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IContinue;

class Continue extends Statement implements IContinue {
	public Continue(IBlock parent) {
		super(parent, true);
	}

	@Override
	public String toString() {
		return "continue";
	}

	@Override
	public void execute(ICallStack stack, List<IValue> expressions) throws ExecutionError {

	}
}