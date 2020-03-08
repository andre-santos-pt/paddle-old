package pt.iscte.paddle.model;

public interface IConditionalExpression extends ICompositeExpression {
	IExpression getConditional();
	IExpression getTrueExpression();
	IExpression getFalseExpression();

	@Override
	default IType getType() {
		return getTrueExpression().getType();
	}

	@Override
	default boolean includes(IVariableDeclaration variable) {
		return getConditional().includes(variable) || 
				getTrueExpression().includes(variable) ||
				getFalseExpression().includes(variable);
	}
	
	@Override
	default boolean isSame(IExpression e) {
		if(e instanceof IConditionalExpression) {
			IConditionalExpression ce = (IConditionalExpression) e;
			return getConditional().isSame(ce.getConditional()) && 
					getTrueExpression().isSame(ce.getTrueExpression()) &&
					getFalseExpression().isSame(ce.getFalseExpression());
		}
		return false;
	}
}
