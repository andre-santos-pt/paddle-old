package pt.iscte.paddle.asg.impl;


import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.IModel2CodeTranslator;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IUnaryExpression;
import pt.iscte.paddle.asg.IUnaryOperator;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IValue;

class UnaryExpression extends Expression implements IUnaryExpression {
	private final IUnaryOperator operator;
	private final ImmutableList<IExpression> operand;
	
	public UnaryExpression(IUnaryOperator operator, IExpression expression) {
		assert operator != null;
		assert expression != null;
		this.operator = operator;
		this.operand = ImmutableList.of(expression);
	}

	
	@Override
	public IType getType() {
		return operator.getResultType(getOperand());
	}

	@Override
	public IUnaryOperator getOperator() {
		return operator;
	}

	@Override
	public IExpression getOperand() {
		return operand.get(0);
	}

	@Override
	public List<IExpression> decompose() {
		return operand;
	}
	
	@Override
	public String toString() {
		return "(" + operator.getSymbol() + getOperand() + ")";
	}
	
	@Override
	public String translate(IModel2CodeTranslator t) {
		return "(" + t.operator(operator) + t.expression(getOperand()) + ")";
	}

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) {
		assert values.size() == 1;
		return getOperator().apply(values.get(0));
	}
}
