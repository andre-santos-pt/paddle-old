package pt.iscte.paddle.asg;

import java.util.List;

public interface IArrayElement extends ICompositeExpression {

	IVariable getVariable();
	
	List<IExpression> getIndexes(); // size() >= 1
	
	default IType getType() {
		IType type = getVariable().getType();
		if(type.isReference())
			type = ((IReferenceType) type).getTarget();
		return ((IArrayType) type).getComponentTypeAt(getIndexes().size());
	}
	
	@Override
	default boolean isDecomposable() {
		return true;
	}
	
	
}
