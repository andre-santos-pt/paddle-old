package pt.iscte.paddle.model;

import java.util.List;

public interface IArrayElement extends ICompositeExpression {

	IExpression getTarget();
	
	List<IExpression> getIndexes(); // size() >= 1
	
	default IType getType() {
		IType type = getTarget().getType();
		if(type.isReference())
			type = ((IReferenceType) type).getTarget();
		return ((IArrayType) type).getComponentTypeAt(getIndexes().size());
	}
	
	@Override
	default boolean isDecomposable() {
		return true;
	}
	
	
}
