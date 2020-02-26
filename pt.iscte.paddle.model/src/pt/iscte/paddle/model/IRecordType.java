package pt.iscte.paddle.model;

import java.util.List;

/**
 * Mutable
 */
public interface IRecordType extends IType {
	
	List<IVariableDeclaration> getFields();
	
	IVariableDeclaration addField(IType type);

	@Override
	default Object getDefaultValue() {
		return null;
	}

	IRecordAllocation heapAllocation();

	
//	TODO IRecordAllocation stackAllocation();

}
