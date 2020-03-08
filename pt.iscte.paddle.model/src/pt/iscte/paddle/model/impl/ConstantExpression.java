package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IConstantDeclaration;
import pt.iscte.paddle.model.IConstantExpression;
import pt.iscte.paddle.model.IVariableDeclaration;

public class ConstantExpression extends Expression implements IConstantExpression {

	private final IConstantDeclaration constant;
	
	public ConstantExpression(IConstantDeclaration constant) {
		this.constant = constant;
	}
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) {
		return stack.getProgramState().getValue(getConstant().getValue().getStringValue());
	}

	@Override
	public IConstantDeclaration getConstant() {
		return constant;
	}
	
	
}
