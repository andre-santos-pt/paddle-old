package pt.iscte.paddle.asg.impl;

import java.util.Iterator;
import java.util.List;

import pt.iscte.paddle.asg.ICompositeExpression;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IExpressionIterator;
import pt.iscte.paddle.machine.IValue;

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
