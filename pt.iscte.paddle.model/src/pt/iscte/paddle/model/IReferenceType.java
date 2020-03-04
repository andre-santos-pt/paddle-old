package pt.iscte.paddle.model;

public interface IReferenceType extends IType {

	IType getTarget();
	
//	@Override
//	default public String getId() {
//		return getTarget().getId() + " *";
//	}

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
