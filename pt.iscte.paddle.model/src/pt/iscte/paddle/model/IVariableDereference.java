package pt.iscte.paddle.model;

public interface IVariableDereference extends ISimpleExpression {

	IVariableExpression getTarget();

	 @Override
	default boolean includes(IVariableDeclaration variable) {
		return getTarget().includes(variable);
	}
	 
	 
	@Override
	default boolean isSame(IExpression e) {
		return e instanceof IVariableDereference &&
				getTarget().isSame(((IVariableDereference) e).getTarget());
	}
	 
	default IExpression length(IExpressionView ... indexes) {
		return getTarget().length(indexes);
	}

	default IExpressionView element(IExpressionView ... views) {
		return getTarget().element(views);
	}
	
}
