package pt.iscte.paddle.model;

public interface IVariableAddress extends ISimpleExpression {

	IVariableExpression getTarget();

	@Override
	default boolean includes(IVariableDeclaration variable) {
		return getTarget().includes(variable);
	}


	@Override
	default boolean isSame(IExpression e) {
		return e instanceof IVariableAddress &&
				getTarget().isSame(((IVariableAddress) e).getTarget());
	}
}
