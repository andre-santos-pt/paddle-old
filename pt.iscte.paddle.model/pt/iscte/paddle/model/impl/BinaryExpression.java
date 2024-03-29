package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IBinaryExpression;
import pt.iscte.paddle.model.IBinaryOperator;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IType;

public class BinaryExpression extends Expression implements IBinaryExpression {
	private final IBinaryOperator operator;
	private final List<IExpression> parts;
	
	public BinaryExpression(IBinaryOperator operator, IExpression left, IExpression right) {
		assert operator != null;
		assert left != null;
		assert right != null;
		this.operator = operator;
		parts = List.of(left, right);
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
	public IType getType() {
		return operator.getResultType(getLeftOperand(), getRightOperand());
	}

	@Override
	public List<IExpression> getParts() {
		return parts;
	}
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		assert values.size() == 2;
		return getOperator().apply(values.get(0), values.get(1));
	}
}
