package pt.iscte.paddle.model;

import pt.iscte.paddle.interpreter.IValue;

// TODO expression iterator
public interface IExpressionIterator {

//	boolean isEmpty();
	
	IExpression next(IValue lastEvaluation);
	
	boolean hasNext(IValue lastEvaluation);
}
