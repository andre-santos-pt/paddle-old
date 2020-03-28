package pt.iscte.paddle.model;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.model.commands.ICommand;
import pt.iscte.paddle.model.impl.Module;
import pt.iscte.paddle.model.validation.ISemanticProblem;

/**
 * Mutable
 */
public interface IModule extends IProgramElement, IListenable<IModule.IListener> {
	
	interface IListener {
		default void commandExecuted(ICommand<?> command) { }
		default void constantAdded(IConstantDeclaration constant) { }
		default void constantRemoved(IConstantDeclaration constant) { }
		default void procedureAdded(IProcedure procedure) { }
		default void procedureRemoved(IProcedure procedure) { }
		
		default void recordTypeAdded(IRecordType type)	{ }
		// TODO record remove
	}
	
	static IModule create() {
		return new Module(true);
	}
	
	static IModule create(String id) {
		Module m = new Module(true);
		m.setId(id);
		return m;
	}
	
	void undo();
	
	void redo();
	
	default Collection<IModule> getImports() {
		// TODO imports
		return ImmutableList.of();
	}
	
	List<IConstantDeclaration> getConstants();
	List<IRecordType> getRecordTypes();
	List<IProcedure> getProcedures();
	

	default IConstantDeclaration addConstant(IType type, ILiteral value, String ... flags) {
		return addConstant(null, type, value, flags);
	}
	
	IConstantDeclaration addConstant(String id, IType type, ILiteral value, String ... flags);
	
	
	IRecordType addRecordType();
	
	IRecordType addRecordType(String id);
	
	
	default IProcedure addProcedure(IType returnType, String ... flags) {
		return addProcedure(null, returnType, flags);
	}
	
//	default IProcedure addProcedure(String id, IType returnType, int index, String ... flags) {
//		return addProcedureAt(null, returnType, index, flags);
//	}
	
	IProcedure addProcedure(String id, IType returnType, String ... flags);
	
	IProcedure addProcedureAt(String id, IType returnType, int index, String ... flags);


	void addProcedure(IProcedure procedure);
	
	void removeProcedure(IProcedure procedure);
	
	void loadBuildInProcedures(Class<?> staticClass);


	IProcedure getProcedure(String id);
	
	IProcedure resolveProcedure(String id, IType ... paramTypes);
	
	IProcedure resolveProcedure(IProcedureDeclaration procedureDeclaration);
	
	List<ISemanticProblem> checkSemantics();

	String translate(IModel2CodeTranslator t);
}
