package pt.iscte.paddle.model;

public interface IVariableExpression extends ISimpleExpression, ITargetExpression {

	IVariableDeclaration getVariable();
	
	IVariableAddress address();

	IVariableDereference dereference();

	
	default boolean isUnbound() {
		return getVariable().isUnbound();
	}
	
	@Override
	default boolean includes(IVariableDeclaration variable) {
		return getVariable() == variable;
	}
	
	@Override
	default boolean isSame(IProgramElement e) {
		return e instanceof IVariableExpression && getVariable().equals(((IVariableExpression) e).getVariable());
	}
	
	@Override
	default String getId() {
		return getVariable().getId();
	}
	
	default boolean is(IVariableDeclaration variable) {
		return getVariable().equals(variable);
	}
}
