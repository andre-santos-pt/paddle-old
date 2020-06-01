package pt.iscte.paddle.model;

import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.commands.ICommand;
import pt.iscte.paddle.model.impl.Module;
import pt.iscte.paddle.model.validation.ISemanticProblem;

/**
 * Mutable
 */
public interface IModule extends IModuleView, IProgramElement, IListenable<IModule.IListener> {
	
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
	
	
	
	

	default IConstantDeclaration addConstant(IType type, ILiteral value, String ... flags) {
		return addConstant(null, type, value, flags);
	}
	
	IConstantDeclaration addConstant(String id, IType type, ILiteral value, String ... flags);
	
	
	IRecordType addRecordType();
	
	IRecordType addRecordType(String id);
	
	IRecordType getRecordType(String id);
	
	default IProcedure addProcedure(IType returnType, String ... flags) {
		return addProcedure(null, returnType, flags);
	}
	
	IProcedure addProcedure(String id, IType returnType, String ... flags);
	
	IProcedure addProcedureAt(String id, IType returnType, int index, String ... flags);


	void addProcedure(IProcedure procedure);
	
	void removeProcedure(IProcedure procedure);
	
	void loadBuiltInProcedures(Class<?> staticClass);
	
	void loadBuiltInProcedures(Executable ... staticMethods);
	
//	void loadBuiltInProcedure(Consumer<IValue> args, IType ... paramTypes);
	
	IProcedure getProcedure(String id);
	
	IProcedure resolveProcedure(String id, IType ... paramTypes);
	
	IProcedure resolveProcedure(IProcedureDeclaration procedureDeclaration);
	
	List<ISemanticProblem> checkSemantics();

	
	default Set<String> getNamespaces() {
		Set<String> set = new HashSet<String>();
		getConstants().forEach(p -> {if(p.getNamespace() != null) set.add(p.getNamespace());});
		getRecordTypes().forEach(p -> {if(p.getNamespace() != null)  set.add(p.getNamespace());});
		getProcedures().forEach(p -> {if(p.getNamespace() != null)  set.add(p.getNamespace());});
		
		return set;
	}
	
	default IModuleView createNamespaceView(String namespace) {
		return new View(this, e -> namespace.equals(e.getNamespace()));
	}
	
	class View implements IModuleView {
		private final IModule module;
		
		private Predicate<INamespaceElement> filter;
		
		View(IModule module, Predicate<INamespaceElement> filter) {
			this.module = module;
			this.filter = filter;
		}
		
		public List<IConstantDeclaration> getConstants() {
			return module.getConstants().stream().filter(filter).collect(Collectors.toList());
		}

		public List<IRecordType> getRecordTypes() {
			return module.getRecordTypes().stream().filter(filter).collect(Collectors.toList());
		}

		public List<IProcedure> getProcedures() {
			return module.getProcedures().stream().filter(filter).collect(Collectors.toList());
		}

//		public String translate(IModel2CodeTranslator t) {
//			// TODO Auto-generated method stub
//			return null;
//		}
		
	}
}
