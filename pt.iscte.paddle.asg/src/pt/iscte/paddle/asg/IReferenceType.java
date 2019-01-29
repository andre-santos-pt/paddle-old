package pt.iscte.paddle.asg;

public interface IReferenceType extends IDataType {

	IDataType getTarget();
	
	@Override
	default public String getId() {
		return "*" + getTarget().getId();
	}

	
}
