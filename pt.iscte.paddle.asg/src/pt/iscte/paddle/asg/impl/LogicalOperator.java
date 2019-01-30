package pt.iscte.paddle.asg.impl;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IBinaryOperator;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IExpressionIterator;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.IValue;
import pt.iscte.paddle.machine.impl.Value;

// TODO step eval
public enum LogicalOperator implements IBinaryOperator {
	AND("&&") {
//		@Override
//		public IValue apply(IExpression left, IExpression right, IStackFrame frame) throws ExecutionError {
//			IValue leftValue =  frame.evaluate(left);
//			if(leftValue == IValue.FALSE)
//				return IValue.FALSE;
//			
//			IValue rightValue = frame.evaluate(right);
//			return IValue.booleanValue(rightValue == IValue.TRUE);
//		}
		
		@Override
		public IValue apply(IValue left, IValue right) throws ExecutionError {
			boolean val = left.getValue().equals(Boolean.TRUE) && right.getValue().equals(Boolean.TRUE);
			return Value.create(IDataType.BOOLEAN, val);
		}
	},
	OR("||") {
//		@Override
//		public IValue apply(IExpression left, IExpression right, IStackFrame frame) throws ExecutionError {
//			IValue leftValue =  frame.evaluate(left);
//			if(leftValue == IValue.TRUE)
//				return IValue.TRUE;
//			IValue rightValue = frame.evaluate(right);
//			return IValue.booleanValue(rightValue == IValue.TRUE);
//		}
		@Override
		public IValue apply(IValue left, IValue right) throws ExecutionError {
			boolean val = left.getValue().equals(Boolean.TRUE) || right.getValue().equals(Boolean.TRUE);
			return Value.create(IDataType.BOOLEAN, val);
		}
	}, 
	XOR("^") {
//		@Override
//		public IValue apply(IExpression left, IExpression right, IStackFrame frame) throws ExecutionError {
//			IValue leftValue =  frame.evaluate(left);
//			IValue rightValue = frame.evaluate(right);
//			return IValue.booleanValue(leftValue != rightValue);
//		}
		@Override
		public IValue apply(IValue left, IValue right) throws ExecutionError {
			boolean val = !left.getValue().equals(right.getValue());
			return Value.create(IDataType.BOOLEAN, val);
		}
	};

	private String symbol;

	private LogicalOperator(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return symbol;
	}

	@Override
	public String getSymbol() {
		return symbol;
	}

	public IDataType getResultType(IExpression left, IExpression right) {
		return IDataType.BOOLEAN;
	}
	
	@Override
	public boolean isValidFor(IDataType left, IDataType right) {
		return left.isBoolean() && right.isBoolean();
	}
	
	@Override
	public OperationType getOperationType() {
		return OperationType.LOGICAL;
	}
	
	@Override
	public IBinaryExpression on(IExpression leftOperand, IExpression rightOperand) {
		return new BinaryExpression(this, leftOperand, rightOperand);
	}
	
	private class Iterator implements IExpressionIterator {

		@Override
		public IExpression next(IValue lastEvaluation) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasNext(IValue lastEvaluation) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
}
