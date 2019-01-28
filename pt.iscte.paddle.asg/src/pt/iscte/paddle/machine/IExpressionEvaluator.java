package pt.iscte.paddle.machine;

import pt.iscte.paddle.asg.IExpression;

public interface IExpressionEvaluator {

	boolean isComplete();

	IValue evaluate() throws ExecutionError;

	IValue getValue();

	Step step() throws ExecutionError;

	class Step {
		final IExpression expression;
		final IValue value;
		 
		public Step(IExpression expression, IValue value) {
			this.expression = expression;
			this.value = value;
		}

		@Override
		public String toString() {
			return expression + " = " + value;
		}
	}
}