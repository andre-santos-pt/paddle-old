package pt.iscte.paddle.model;

import java.util.List;

import com.google.common.collect.ImmutableList;

public interface IVariableAssignment extends IStatement {
	// OCL: variable must be owned by the same procedure
	IVariable getVariable();
	IExpression getExpression();
	IBlock getParent();
	
	default boolean isIncrement() {
		if(!(getExpression() instanceof IBinaryExpression))
			return false;
		
		IBinaryExpression exp = (IBinaryExpression) getExpression();
		return exp.getOperator().equals(IOperator.ADD) &&
				exp.getLeftOperand().equals(getVariable()) && exp.getRightOperand().equals(IType.INT.literal(1)) ||
				exp.getRightOperand().equals(getVariable()) && exp.getLeftOperand().equals(IType.INT.literal(1));
				
	}
	
	@Override
	default List<IExpression> getExpressionParts() {
		return ImmutableList.of(getExpression());
	}
	
	
	
	
}