package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IBlockElement;
import pt.iscte.paddle.model.IBreak;

class Break extends Statement implements IBreak {
		public Break(IBlock parent) {
			super(parent, true);
		}
		
		@Override
		public String toString() {
			return "break";
		}
		
		@Override
		public void execute(ICallStack stack, List<IValue> expressions) throws ExecutionError {

		}
		
		@Override
		public String getExplanation(ICallStack stack, List<IValue> expressions) {
			List<IBlockElement> children = ((IBlock) getParent().getParent()).getChildren();
			int i = children.indexOf(this);
			return "Exits the loop, proceeding at " + (i + 1  == children.size() ? "??" : children.get(i+1));
		}
	}