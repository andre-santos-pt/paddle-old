package pt.iscte.paddle.model;

public interface IConstantExpression extends ISimpleExpression {

	IConstantDeclaration getConstant();
	
	default IType getType() {
		return getConstant().getType();
	}
	
	default String getStringValue() {
		return getConstant().getValue().getStringValue();
	}
	
	default boolean includes(IVariableDeclaration variable) {
		return false;
	}
	
	@Override
	default boolean isSame(IProgramElement e) {
		return e instanceof IConstantExpression &&
				getConstant().equals(((IConstantExpression) e).getConstant());
	}
	
}
