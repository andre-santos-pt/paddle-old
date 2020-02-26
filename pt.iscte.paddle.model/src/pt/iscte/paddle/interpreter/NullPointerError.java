package pt.iscte.paddle.interpreter;

import pt.iscte.paddle.model.IExpression;

public class NullPointerError extends ExecutionError {
	private static final long serialVersionUID = 1L;

	public NullPointerError(IExpression expression) {
		super(Type.NULL_POINTER, expression, "null pointer access");
	}

}
