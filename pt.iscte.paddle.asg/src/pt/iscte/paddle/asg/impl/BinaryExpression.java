package pt.iscte.paddle.asg.impl;

import java.util.List;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IBinaryOperator;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IValue;

class BinaryExpression extends Expression implements IBinaryExpression {
	private final IBinaryOperator operator;
	private final IExpression left;
	private final IExpression right;
	
	public BinaryExpression(IBinaryOperator operator, IExpression left, IExpression right) {
		assert operator != null;
		assert left != null;
		assert right != null;
		this.operator = operator;
		this.left = left;
		this.right = right;
	}

	@Override
	public IBinaryOperator getOperator() {
		return operator;
	}

	@Override
	public IExpression getLeftExpression() {
		return left;
	}

	@Override
	public IExpression getRightExpression() {
		return right;
	}
	
	@Override
	public IDataType getType() {
		return operator.getResultType(left, right);
	}

	@Override
	public boolean isDecomposable() {
		return true;
	}	
	
	@Override
	public String toString() {
		String l = left.toString();
		if(left instanceof IBinaryExpression)
			l = "(" + l + ")";
		
		String r = right.toString();
		if(right instanceof IBinaryExpression)
			r = "(" + r + ")";
		return l + " " + operator + " " + r;
	}
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		assert values.size() == 2;
		return getOperator().apply(values.get(0), values.get(1));
	}
}
