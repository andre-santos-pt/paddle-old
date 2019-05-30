package pt.iscte.paddle.asg;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.IModel2CodeTranslator;
import pt.iscte.paddle.asg.impl.Module;
import pt.iscte.paddle.asg.semantics.ISemanticProblem;

/**
 * Mutable
 */
public interface IModule extends IProgramElement {

	static IModule create() {
		return new Module();
	}
	
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

	void loadBuildInProcedures(Class<?> staticClass);


	
	IProcedure resolveProcedure(String id, IType ... paramTypes);
	
	IProcedure resolveProcedure(IProcedureDeclaration procedureDeclaration);
	
	List<ISemanticProblem> checkSemantics();


	String translate(IModel2CodeTranslator t);
}
