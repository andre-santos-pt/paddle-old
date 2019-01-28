package pt.iscte.paddle.asg;

import java.util.List;

/**
 * Mutable
 */
public interface IStructType extends IDataType {
	
	List<IVariable> getMemberVariables();
	
	IVariable addMemberVariable(String id, IDataType type);

	@Override
	default Object getDefaultValue() {
		return null;
	}

	IStructAllocation allocationExpression();
}
