package pt.iscte.paddle.asg;

import java.util.List;

/**
 * Mutable
 */
public interface IRecordType extends IType {
	
	List<IVariable> getFields();
	
	IVariable addMemberVariable(IType type);

	@Override
	default Object getDefaultValue() {
		return null;
	}

	IRecordAllocation allocationExpression();
}
