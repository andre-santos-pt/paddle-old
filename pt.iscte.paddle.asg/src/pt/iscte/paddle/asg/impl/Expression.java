package pt.iscte.paddle.asg.impl;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.asg.IConditionalExpression;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IExpressionIterator;
import pt.iscte.paddle.machine.IEvaluable;
import pt.iscte.paddle.machine.IValue;

abstract class Expression extends ProgramElement implements IEvaluable, IExpression {

	@Override
	public IConditionalExpression conditional(IExpression trueCase, IExpression falseCase) {
		return new Conditional(this, trueCase, falseCase);
	}	

	// TODO evaluate only one
	// ideia: decompose() -> iterador que para quando nao e preciso mais
	static class Conditional extends ProgramElement implements IConditionalExpression {
		private ImmutableList<IExpression> parts;
		
		public Conditional(IExpression condition, IExpression trueCase, IExpression falseCase) {
			parts = ImmutableList.of(condition, trueCase, falseCase);
		}
		
		@Override
		public boolean isDecomposable() {
			return true;
		}

		@Override
		public List<IExpression> decompose() {
			return parts;
		}

		@Override
		public IConditionalExpression conditional(IExpression trueCase, IExpression falseCase) {
			return new Conditional(this, trueCase, falseCase);
		}

		@Override
		public IExpression getConditional() {
			return parts.get(0);
		}

		@Override
		public IExpression getTrueExpression() {
			return parts.get(1);
		}

		@Override
		public IExpression getFalseExpression() {
			return parts.get(2);
		}	
		
		class Iterator implements IExpressionIterator {
			boolean condition = false;
			boolean over = false;

			@Override
			public IExpression next(IValue lastEvaluation) {
				if(!condition) {
					assert lastEvaluation == null;
					condition = true;
					return getConditional();
				}
				else {
					assert lastEvaluation != null;
					over = true;
					if(lastEvaluation.isTrue())
						return getTrueExpression();
					else
						return getFalseExpression();
				}
			}

			@Override
			public boolean hasNext(IValue lastEvaluation) {
				return !over;
			}
		}
	}
}
