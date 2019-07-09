package pt.iscte.paddle.interpreter.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IExpressionEvaluator;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IProcedureCall;

public class ExpressionEvaluator implements IExpressionEvaluator {

	private ICallStack callStack;
	private Stack<IExpression> expStack;
	private Stack<IValue> valueStack;

	public ExpressionEvaluator(IExpression expression, ICallStack callStack)  {
		this.callStack = callStack;
		expStack = new Stack<IExpression>();
		valueStack = new Stack<IValue>();
		expStack.push(expression);
		System.out.println(expression + "  " + expression.getParts());
	}

	
	@Override
	public boolean isComplete() {
		return expStack.isEmpty();
	}

	@Override
	public IExpression currentExpression() {
		assert !isComplete();
		return expStack.peek();
	}
	
	@Override
	public IValue evaluate() throws ExecutionError {
		while(!expStack.isEmpty())
			step();

		return getValue();
	}

	@Override
	public IValue getValue() {
		assert isComplete();
		System.out.println("** " + valueStack.peek());
		return valueStack.peek();
	}

	@Override
	public Step step() throws ExecutionError {
		assert !isComplete();

		while(expStack.peek().isDecomposable() && valueStack.size() < expStack.peek().getNumberOfParts()) {
			expStack.peek().getParts().forEach(e -> expStack.push(e));

			while(!expStack.peek().isDecomposable())
				valueStack.push(callStack.getTopFrame().evaluate(expStack.pop(), ImmutableList.of()));
		}

		int parts = expStack.peek().getNumberOfParts();
		List<IValue> values = new ArrayList<>();
		while(parts-- > 0)
			values.add(valueStack.pop());

		IValue val = callStack.getTopFrame().evaluate(expStack.peek(), values);
		if(val == null) {
			IProcedureCall callExp = (IProcedureCall) expStack.pop();
			expStack.push(new ProcedureReturnExpression(callExp.getProcedure()));
		}
		else
			valueStack.push(val);

		return new Step(val == null ? null : expStack.pop(), val);
	}
}
