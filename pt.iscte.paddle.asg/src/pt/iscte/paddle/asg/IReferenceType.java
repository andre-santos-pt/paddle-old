package pt.iscte.paddle.asg;

public interface IReferenceType extends IDataType {

	IDataType getTarget();
	
	@Override
	default public String getId() {
		return "*" + getTarget().getId();
	}

	@Override
	default boolean isCompatible(IDataType type) {
		return type instanceof IReferenceType && 
				((IReferenceType) type).getTarget().isCompatible(getTarget());
	}
	
	default IDataType resolveTarget() {
		IDataType t = getTarget();
		while(t instanceof IReferenceType)
			t = ((IReferenceType) t).getTarget();
		
		return t;
	}
	
}
