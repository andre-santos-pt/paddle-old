package pt.iscte.paddle.asg;

import java.util.List;

import com.google.common.collect.ImmutableList;

// TODO remove?
public interface IConstantExpression extends IExpression {

	IConstant getConstant();
	
	@Override
	default List<IExpression> decompose() {
		return ImmutableList.of();
	}
	
	@Override
	default boolean isDecomposable() {
		return false;
	}
}
