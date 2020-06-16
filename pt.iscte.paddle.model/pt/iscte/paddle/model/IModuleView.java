package pt.iscte.paddle.model;

import java.util.List;

public interface IModuleView extends IProgramElement, IListenable<IModule.IListener>  {

	List<IConstantDeclaration> getConstants();
	List<IRecordType> getRecordTypes();
	List<IProcedure> getProcedures();
	
	
}
