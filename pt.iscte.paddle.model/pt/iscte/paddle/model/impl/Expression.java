package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IEvaluable;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IArrayElement;
import pt.iscte.paddle.model.IArrayLength;
import pt.iscte.paddle.model.IConditionalExpression;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IExpressionIterator;
import pt.iscte.paddle.model.IModuleTranslator;
import pt.iscte.paddle.model.IRecordFieldExpression;
import pt.iscte.paddle.model.ITargetExpression;
import pt.iscte.paddle.model.IVariableDeclaration;

abstract class Expression extends ProgramElement implements IEvaluable, IExpression {

	
	Expression(String...flags) {
		super(flags);
	}
	
	@Override
	public IRecordFieldExpression field(IVariableDeclaration field) {
		assert this instanceof ITargetExpression;
		return new RecordFieldExpression((ITargetExpression)this, field);
	}	
	
	@Override
	public IArrayLength length(List<IExpression> indexes) {
		assert this instanceof ITargetExpression;
		return new ArrayLength((ITargetExpression) this, indexes);
	}
	
	@Override
	public IArrayElement element(List<IExpression> indexes) {
		assert this instanceof ITargetExpression;
		return new ArrayElement((ITargetExpression) this, indexes);
	}
	
	@Override
	public IConditionalExpression conditional(IExpression trueCase, IExpression falseCase) {
		return new Conditional(this, trueCase, falseCase);
	}	

	@Override
	public final String toString() {
		return Translator.INSTANCE.translate(this);
	}
	
	// TODO evaluate only one
	// ideia: decompose() -> iterador que para quando nao e preciso mais
	static class Conditional extends Expression implements IConditionalExpression, IEvaluable {
		private List<IExpression> parts;
		
		public Conditional(IExpression condition, IExpression trueCase, IExpression falseCase) {
			parts = List.of(condition, trueCase, falseCase);
		}
		
		@Override
		public List<IExpression> getParts() {
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
		
		@Override
		public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
			return values.get(0).isTrue() ? values.get(1) : values.get(2);
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
