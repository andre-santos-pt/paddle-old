package pt.iscte.paddle.model;

public interface IArrayElement extends ICompositeExpression, IArrayAccess {

	
	default IType getType() {
		IType type = getTarget().getType();
		if(type.isReference())
			type = ((IReferenceType) type).getTarget();
		
		if(type instanceof IArrayType)
			return ((IArrayType) type).getComponentTypeAt(getIndexes().size());
		else {
			System.err.println("on IArrayElement " + type);
			return type;
		}
			
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
