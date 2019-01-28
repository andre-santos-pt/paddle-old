package pt.iscte.paddle.asg.impl;


import java.util.List;

import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IUnaryExpression;
import pt.iscte.paddle.asg.IUnaryOperator;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IValue;

class UnaryExpression extends Expression implements IUnaryExpression {

	private final IUnaryOperator operator;
	private final IExpression expression;
	
	public UnaryExpression(IUnaryOperator operator, IExpression expression) {
		assert operator != null;
		assert expression != null;
		this.operator = operator;
		this.expression = expression;
	}

	
	@Override
	public IDataType getType() {
		return operator.getResultType(expression);
	}

	@Override
	public IUnaryOperator getOperator() {
		return operator;
	}

	@Override
	public IExpression getExpression() {
		return expression;
	}

	@Override
	public boolean isDecomposable() {
		return true;
	}	
	
	@Override
	public String toString() {
		return operator.getSymbol() + "(" + expression + ")";
	}

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) {
		assert values.size() == 1;
		return getOperator().apply(values.get(0));
	}
}
