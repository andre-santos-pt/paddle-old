package pt.iscte.paddle.machine.impl;

import java.util.Stack;

import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProgramElement;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.IValue;

public class ProcedureExecutor {

	private Stack<BlockIterator> stack;
	
	public ProcedureExecutor(IProcedure procedure) {
		stack = new Stack<BlockIterator>();
		if(!procedure.getBody().isEmpty())
			stack.push(new BlockIterator(procedure.getBody()));
	}
	
	public boolean isOver() {
		return stack.isEmpty();
	}
	
	public void moveNext(IValue last) throws ExecutionError {
		assert !isOver();
		
		if(stack.peek().hasNext()) {
			BlockIterator child = stack.peek().moveNext(last);
			if(child != null)
				stack.push(child);
		}
		while(!stack.isEmpty() && !stack.peek().hasNext())
			stack.pop();
	}
	
	public IProgramElement current() {
		assert !isOver();
		return stack.peek().current();
	}
	
	
}
