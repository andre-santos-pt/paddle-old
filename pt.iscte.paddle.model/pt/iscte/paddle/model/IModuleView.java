package pt.iscte.paddle.model;

import java.util.List;

public interface IModuleView {

	List<IConstantDeclaration> getConstants();
	List<IRecordType> getRecordTypes();
	List<IProcedure> getProcedures();
	
}
