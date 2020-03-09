package pt.iscte.paddle.model;

import java.util.List;

public interface IArrayAllocation extends ICompositeExpression {
	List<IExpression> getDimensions();
	
	boolean onHeapMemory();
	
	@Override
	default boolean includes(IVariableDeclaration variable) {
		for(IExpression e : getDimensions())
			if(e.includes(variable))
				return true;
		return false;
	}
	
	@Override
	default boolean isSame(IProgramElement e) {
		return e instanceof IArrayAllocation &&
				getType().isSame(((IArrayAllocation) e).getType()) &&
				IExpression.areSame(getDimensions(), ((IArrayAllocation) e).getDimensions());
	}
}
