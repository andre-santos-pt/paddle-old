package pt.iscte.paddle.model;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.model.impl.Module;
import pt.iscte.paddle.model.validation.ISemanticProblem;

/**
 * Mutable
 */
public interface IModule extends IProgramElement, IListenable<IModule.IListener> {
	
	interface IListener {
//		default void commandExecuted(ICommand<?> command) { }
		default void constantAdded(IConstant constant) { }
		default void constantDeleted(IConstant constant) { }
		default void procedureAdded(IProcedure procedure) { }
		default void procedureDeleted(IProcedure procedure) { }
	}
	
	static IModule create() {
		return new Module(true);
	}
	
	void undo();
	
	void redo();
	
	default Collection<IModule> getImports() {
		// TODO imports
		return ImmutableList.of();
	}
	
	Collection<IConstant> getConstants();
	Collection<IRecordType> getRecordTypes();
	Collection<IProcedure> getProcedures();
	

	IConstant addConstant(IType type, ILiteral value);
	IRecordType addRecordType();
	IProcedure addProcedure(IType returnType);

	void addProcedure(IProcedure procedure);
	
	void loadBuildInProcedures(Class<?> staticClass);


	IProcedure getProcedure(String id);
	
	IProcedure resolveProcedure(String id, IType ... paramTypes);
	
	IProcedure resolveProcedure(IProcedureDeclaration procedureDeclaration);
	
	List<ISemanticProblem> checkSemantics();

	String translate(IModel2CodeTranslator t);
}
