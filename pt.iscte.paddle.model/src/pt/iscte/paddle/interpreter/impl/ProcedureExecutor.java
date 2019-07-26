package pt.iscte.paddle.interpreter.impl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IExpressionEvaluator;
import pt.iscte.paddle.interpreter.IExpressionEvaluator.Step;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IStatement;

public class ProcedureExecutor {
	private StackFrame frame;
	private Stack<BlockIterator> stack;
	private IExpressionEvaluator pendingExpression;
	private Queue<IExpressionEvaluator> pendingEvaluations;
	private List<IValue> values;

	public ProcedureExecutor(StackFrame frame) {
		this.frame = frame;
		stack = new Stack<BlockIterator>();
		IBlock body = frame.getProcedure().getBody();
		if(!body.isEmpty())
			stack.push(new BlockIterator(body));
	}

	public boolean isOver() {
		return stack.isEmpty() && pendingEvaluations == null && frame.isTopFrame();
	}

	private void moveNext(IValue last) throws ExecutionError {
		assert !isOver();

		if(stack.peek().hasNext()) {
			BlockIterator child = stack.peek().moveNext(last);
			if(child != null)
				stack.push(child);
		}

		while(!stack.isEmpty() && !stack.peek().hasNext())
			stack.pop();
	}

	public IProgramElement nextInstruction() {
		assert !isOver();
		if(pendingExpression != null && !pendingExpression.isComplete())
			return pendingExpression.currentExpression();
		else
			return stack.peek().current();
	}



	public void stepIn() throws ExecutionError {
		assert !isOver();

		IProgramElement current = stack.peek().current();
		if(current instanceof IStatement) {
			IStatement s = (IStatement) current;
			if(pendingEvaluations == null) {
				pendingEvaluations = new ArrayDeque<>();
				values = new ArrayList<IValue>();
				for(IExpression e : s.getExpressionParts())
					pendingEvaluations.offer(new ExpressionEvaluator(e, frame.getCallStack()));

				if(pendingEvaluations.isEmpty()) {
					frame.execute(s, values);
					values = null;
					pendingEvaluations = null;
					moveNext(null);
				}
				else {
					pendingEvaluations.peek().step();
				}
			}
			else if(!pendingEvaluations.isEmpty()) {
				if(pendingEvaluations.peek().isComplete()) {
					IExpressionEvaluator e = pendingEvaluations.poll();
					values.add(e.getValue());
					if(pendingEvaluations.isEmpty()) {
						frame.execute(s, values);
						values = null;
						pendingEvaluations = null;
						moveNext(null);
					}
				}
				else {
					pendingEvaluations.peek().step();
				}
			}
			else {
				frame.execute(s, values);
				values = null;
				pendingEvaluations = null;
				moveNext(null);
			}
		}
		else if(current instanceof IExpression) {
			IExpression e = (IExpression) current;
			if(pendingExpression == null) {
				pendingExpression = new ExpressionEvaluator(e, frame.getCallStack());
				Step step = pendingExpression.step();
			}
			else if(!pendingExpression.isComplete()) {
				Step step = pendingExpression.step();
				if(pendingExpression.isComplete()) {
					IValue value = pendingExpression.getValue();
					pendingExpression = null;
					moveNext(value);
				}
			}
			else {
				IValue value = pendingExpression.getValue();
				pendingExpression = null;
				moveNext(value);
			}
		}
		else {
			moveNext(null);
		}
	}

}
