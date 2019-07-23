package pt.iscte.paddle.interpreter.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IExpressionEvaluator;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IProcedureCall;
import pt.iscte.paddle.model.ISimpleExpression;

public class ExpressionEvaluator implements IExpressionEvaluator {

	private IExpression expression;
	private ICallStack callStack;

	private List<IExpression> parts;
	private List<Object> partial;
	private int next;
	private IValue result;
	
	public ExpressionEvaluator(IExpression expression, ICallStack callStack)  {
		this.expression = expression;
		this.callStack = callStack;

		parts = expression.getParts();
		partial = new ArrayList<>(parts.size());	
		for(IExpression e : parts) {
			if(e instanceof ISimpleExpression)
				partial.add(e);
			else 
				partial.add(new ExpressionEvaluator(e, callStack));
		}
		next = 0;
		result = null;
	}

	@Override
	public String toString() {
		return expression + " = " + parts + " * " + next;
	}
	
	@Override
	public boolean isComplete() {
		return result != null;
	}

	@Override
	public IValue getValue() {
		assert isComplete();
		return result;
	}

	@Override
	public IExpression currentExpression() {
		assert !isComplete();
		if(next == parts.size())
			return expression;
		else if(partial.get(next) instanceof ISimpleExpression)
			return (ISimpleExpression) partial.get(next);
		else
			return ((ExpressionEvaluator) partial.get(next)).currentExpression();
	}
	
	@Override
	public IValue evaluate() throws ExecutionError {
		while(!isComplete())
			step();

		return getValue();
	}


	@Override
	public Step step() throws ExecutionError {
		assert !isComplete();
		
		if(next == parts.size()) {
			List<IValue> values = new ArrayList<>();
			partial.forEach(p -> values.add((IValue) p));
			result = callStack.getTopFrame().evaluate(expression, values);
			if(result == null) {
				expression = new ProcedureReturnExpression(((IProcedureCall) expression).getProcedure());
			}
			return new Step(result == null ? null : expression, result);
		}
		else {
			
			if(partial.get(next) instanceof ISimpleExpression) {
				ISimpleExpression exp = (ISimpleExpression) partial.get(next);
				IValue r = callStack.getTopFrame().evaluate(exp, Collections.emptyList());
				Step step = new Step(parts.get(next), r);
				partial.set(next, r);
				next++;
				return step;
			}
			else {
				ExpressionEvaluator eval = (ExpressionEvaluator) partial.get(next);
				if(eval.isComplete()) {
					IValue r = eval.getValue();
					Step step = new Step(parts.get(next), r);
					partial.set(next, r);
					next++;
					return step;
				}
				else {
					return eval.step();
				}
			}
		}
	}
}
