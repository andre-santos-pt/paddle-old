package pt.iscte.paddle.interpreter;

import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IProgramElement;

public class ArrayIndexError extends ExecutionError {
	private static final long serialVersionUID = 1L;
	
	private final int invalidIndex;
	private final IExpression target;
	private final IExpression indexExpression;
	private final int indexDimension;

	public ArrayIndexError(IProgramElement element, int invalidIndex, IExpression target, IExpression indexExpression, int indexDimension) {
		super(Type.ARRAY_INDEX_BOUNDS, element, "invalid array index access");
		this.invalidIndex = invalidIndex;
		this.target = target;
		this.indexExpression = indexExpression;
		this.indexDimension = indexDimension;
	}
	
	public int getInvalidIndex() {
		return invalidIndex;
	}
	
	public IExpression getIndexExpression() {
		return indexExpression;
	}

	public IExpression getTarget() {
		return target;
	}
	
	public int getIndexDimension() {
		return indexDimension;
	}
	
	@Override
	public String toString() {
		return super.toString() + 
				" ; invalid index: " + invalidIndex + " ; target: " + target + 
				" ; expression: " + indexExpression + " ; dimension: " + indexDimension ;
	}
}
