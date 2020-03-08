package pt.iscte.paddle.model;

import java.util.List;

public interface IArrayLength extends ICompositeExpression {
	IExpression getTarget();
	List<IExpression> getIndexes(); // size() >= 1
	
	@Override
	default boolean includes(IVariableDeclaration variable) {
		if(getTarget().includes(variable))
			return true;
		
		for(IExpression e : getIndexes())
			if(e.includes(variable))
				return true;
		
		return false;
	}
	
	@Override
	default boolean isSame(IExpression e) {
		return e instanceof IArrayLength &&
				getTarget().isSame(((IArrayLength) e).getTarget()) &&
				IExpression.areSame(getIndexes(), ((IArrayLength) e).getIndexes());
	}
}
