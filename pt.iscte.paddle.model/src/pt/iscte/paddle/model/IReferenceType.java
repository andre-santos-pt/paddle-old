package pt.iscte.paddle.model;

public interface IReferenceType extends IType {

	IType getTarget();
	
	@Override
	default boolean isSame(IProgramElement e) {
		return 	e instanceof IReferenceType && getTarget().isSame(((IReferenceType) e).getTarget());
	}

	@Override
	default boolean isCompatible(IType type) {
		return type instanceof IReferenceType && 
				((IReferenceType) type).getTarget().isCompatible(getTarget());
	}
	
	default IType resolveTarget() {
		IType t = getTarget();
		while(t instanceof IReferenceType)
			t = ((IReferenceType) t).getTarget();
		
		return t;
	}
	
	@Override
	default IExpression getDefaultExpression() {
		return ILiteral.NULL;
	}
	
}
