package pt.iscte.paddle.asg;

import java.util.List;

import com.google.common.collect.ImmutableList;

public interface IVariableExpression extends IExpression {
	// TODO OCL: variable must be owned by the same procedure of expression
	IVariable getVariable();
	
//	boolean isAddress();
//	boolean isValue();
	
	@Override
	default IDataType getType() {
		return getVariable().getType();
	}

	@Override
	default List<IExpression> decompose() {
		return ImmutableList.of();
	}
	
	@Override
	default boolean isDecomposable() {
		return false;
	}
	
	
	
}
