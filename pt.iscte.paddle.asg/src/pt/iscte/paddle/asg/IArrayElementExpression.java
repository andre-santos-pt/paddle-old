package pt.iscte.paddle.asg;

import java.util.List;

public interface IArrayElementExpression extends IExpression {

	IVariable getVariable();
	
	List<IExpression> getIndexes(); // size() >= 1
	
	default IDataType getType() {
		IDataType type = getVariable().getType();
		if(type.isReference())
			type = ((IReferenceType) type).getTarget();
		return ((IArrayType) type).getComponentType();
	}
	
	@Override
	default boolean isDecomposable() {
		return true;
	}
	
	
}
