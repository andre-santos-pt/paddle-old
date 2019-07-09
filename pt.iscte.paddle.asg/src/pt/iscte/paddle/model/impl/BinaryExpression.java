package pt.iscte.paddle.model.impl;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IBinaryExpression;
import pt.iscte.paddle.model.IBinaryOperator;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IModel2CodeTranslator;
import pt.iscte.paddle.model.IType;

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
	public String translate(IModel2CodeTranslator t) {
		String l = getLeftOperand().translate(t);
		if(getLeftOperand() instanceof IBinaryExpression)
			l = "(" + l + ")";
		
		String r = getRightOperand().translate(t);
		if(getRightOperand() instanceof IBinaryExpression)
			r = "(" + r + ")";
		return l + " " + t.operator(operator) + " " + r;
	}
	
	
}
