package pt.iscte.paddle.model.impl;


import java.util.List;

import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IModel2CodeTranslator;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IUnaryExpression;
import pt.iscte.paddle.model.IUnaryOperator;

public class UnaryExpression extends Expression implements IUnaryExpression {
	private final IUnaryOperator operator;
	private final List<IExpression> operand;
	
	public UnaryExpression(IUnaryOperator operator, IExpression expression) {
		assert operator != null;
		assert expression != null;
		this.operator = operator;
		this.operand = List.of(expression);
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
	public List<IExpression> getParts() {
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
