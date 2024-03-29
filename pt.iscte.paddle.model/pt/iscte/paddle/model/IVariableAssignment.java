package pt.iscte.paddle.model;

import java.util.List;

public interface IVariableAssignment extends IStatement {
	// OCL: variable must be owned by the same procedure
	IVariableDeclaration getTarget();
	IExpression getExpression();
	IBlock getParent();
	
	@Override
	default List<IExpression> getExpressionParts() {
		return List.of(getExpression());
	}
	
	default boolean isIncrement() {
		return isModifiedByOne(this, IOperator.ADD);
	}
	
	default boolean isDecrement() {
		return isModifiedByOne(this, IOperator.SUB);
	}

	@Override
	default boolean isSame(IProgramElement s) {
		return s instanceof IVariableAssignment &&
				getTarget().equals(((IVariableAssignment) s).getTarget()) &&
				getExpression().isSame(((IVariableAssignment) s).getExpression());
	}

	private static boolean isModifiedByOne(IVariableAssignment ass, IBinaryOperator op) {
		if(!(ass.getExpression() instanceof IBinaryExpression))
			return false;
		
		final ILiteral ONE = IType.INT.literal(1);
		IBinaryExpression exp = (IBinaryExpression) ass.getExpression();
		return exp.getOperator().isSame(op) &&
				exp.getLeftOperand().isSame(ass.getTarget().expression()) && exp.getRightOperand().isSame(ONE) ||
				exp.getRightOperand().isSame(ass.getTarget().expression()) && exp.getLeftOperand().isSame(ONE);
	}
}
