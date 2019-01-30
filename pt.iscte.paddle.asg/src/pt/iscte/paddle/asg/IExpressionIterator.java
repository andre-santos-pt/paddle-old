package pt.iscte.paddle.asg;

import pt.iscte.paddle.machine.IValue;

public interface IExpressionIterator {

//	boolean isEmpty();
	
	IExpression next(IValue lastEvaluation);
	
	boolean hasNext(IValue lastEvaluation);
}
