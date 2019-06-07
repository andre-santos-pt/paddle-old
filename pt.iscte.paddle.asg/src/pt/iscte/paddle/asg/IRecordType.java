package pt.iscte.paddle.asg;

import java.util.List;

/**
 * Mutable
 */
public interface IRecordType extends IType {
	
	List<IVariable> getFields();
	
	IVariable addField(IType type);

	@Override
	default Object getDefaultValue() {
		return null;
	}

	IRecordAllocation heapAllocation();

	
//	TODO IRecordAllocation stackAllocation();

}
