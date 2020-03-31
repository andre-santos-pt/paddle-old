package pt.iscte.paddle.model;

public interface IVariableDereference extends ISimpleExpression {

	IVariableExpression getTarget();

	 @Override
	default boolean includes(IVariableDeclaration variable) {
		return getTarget().includes(variable);
	}
	 
	 
	@Override
	default boolean isSame(IProgramElement e) {
		return e instanceof IVariableDereference &&
				getTarget().isSame(((IVariableDereference) e).getTarget());
	}
}
