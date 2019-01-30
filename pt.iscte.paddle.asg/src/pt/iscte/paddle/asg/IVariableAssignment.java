package pt.iscte.paddle.asg;

import java.util.List;

import com.google.common.collect.ImmutableList;

public interface IVariableAssignment extends IStatement {
	// OCL: variable must be owned by the same procedure
	IVariable getVariable();
	IExpression getExpression();
	boolean onTarget();
	
	@Override
	default List<IExpression> getExpressionParts() {
		return ImmutableList.of(getExpression());
	}
	
	
}
