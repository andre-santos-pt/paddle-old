package pt.iscte.paddle.asg;

import java.util.List;

public interface IArrayElementExpression extends IVariableExpression {

	IVariable getVariable();
	
	List<IExpression> getIndexes(); // size() >= 1
	
	default IDataType getType() {
		return ((IArrayType) getVariable().getType()).getComponentType();
	}
	
	@Override
	default boolean isDecomposable() {
		return true;
	}
	
	
}
