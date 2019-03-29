package pt.iscte.paddle.asg;

import java.util.List;

/**
 * Mutable
 */
public interface IRecordType extends IDataType {
	
	List<IVariable> getMemberVariables();
	
	IVariable addMemberVariable(IDataType type);

	@Override
	default Object getDefaultValue() {
		return null;
	}

	IRecordAllocation allocationExpression();
}
