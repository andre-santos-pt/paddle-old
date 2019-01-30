package pt.iscte.paddle.asg.impl;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IBinaryOperator;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IValue;

public class BinaryExpression extends Expression implements IBinaryExpression {
	private final IBinaryOperator operator;
	private final ImmutableList<IExpression> parts;
	
	public BinaryExpression(IBinaryOperator operator, IExpression left, IExpression right) {
		assert operator != null;
		assert left != null;
		assert right != null;
		this.operator = operator;
		parts = ImmutableList.of(left, right);
	}

	@Override
	public IBinaryOperator getOperator() {
		return operator;
	}

	@Override
	public IExpression getLeftOperand() {
		return parts.get(0);
	}

	@Override
	public IExpression getRightOperand() {
		return parts.get(1);
	}
	
	@Override
	public IDataType getType() {
		return operator.getResultType(getLeftOperand(), getRightOperand());
	}

	@Override
	public List<IExpression> decompose() {
		return parts;
	}
	
	@Override
	public String toString() {
		String l = getLeftOperand().toString();
		if(getLeftOperand() instanceof IBinaryExpression)
			l = "(" + l + ")";
		
		String r = getRightOperand().toString();
		if(getRightOperand() instanceof IBinaryExpression)
			r = "(" + r + ")";
		return l + " " + operator.getSymbol() + " " + r;
	}
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		assert values.size() == 2;
		return getOperator().apply(values.get(0), values.get(1));
	}
}
