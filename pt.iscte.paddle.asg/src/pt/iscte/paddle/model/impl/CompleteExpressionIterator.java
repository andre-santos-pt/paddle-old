package pt.iscte.paddle.model.impl;

import java.util.Iterator;
import java.util.List;

import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IExpressionIterator;

public class CompleteExpressionIterator implements IExpressionIterator {

	private Iterator<IExpression> iterator;

	public CompleteExpressionIterator(List<IExpression> list) {
		iterator = list.iterator();
	}
	
	@Override
	public IExpression next(IValue lastEvaluation) {
		return iterator.next();
	}

	@Override
	public boolean hasNext(IValue lastEvaluation) {
		return iterator.hasNext();
	}

}
