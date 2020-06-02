package pt.iscte.paddle.model;

import java.util.List;

public interface IArrayElement extends ICompositeExpression, ITargetExpression {

	ITargetExpression getTarget();
	
	List<IExpression> getIndexes(); // size() >= 1
	
	default IType getType() {
		IType type = getTarget().getType();
		if(type.isReference())
			type = ((IReferenceType) type).getTarget();
		
		return type;
//		return ((IArrayType) type).getComponentTypeAt(getIndexes().size());
	}
	
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
	default boolean isSame(IProgramElement e) {
		return e instanceof IArrayElement &&
				getTarget().isSame(((IArrayElement) e).getTarget()) &&
				IExpression.areSame(getIndexes(), ((IArrayElement)e).getIndexes());
	}
}
