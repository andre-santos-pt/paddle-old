package pt.iscte.paddle.model;

import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
import pt.iscte.paddle.model.impl.Translator;
import pt.iscte.paddle.model.validation.ISemanticProblem;

/**
 * Mutable
 */
public interface IModule extends IModuleView, IListenable<IModule.IListener> {

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

	default IProcedure addProcedure(IType returnType) {
		return addProcedureAt(returnType, -1, p -> {});
	}
	
	default IProcedure addProcedure(String id, IType returnType) {
		return addProcedureAt(returnType, -1, p -> p.setId(id));
	}
	
	default IProcedure addProcedure(IType returnType, Consumer<IProcedure> configure) {
		return addProcedureAt(returnType, -1, configure);
	}

	IProcedure addProcedureAt(IType returnType, int index, Consumer<IProcedure> configure);


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
		if(namespace == null)
			return new NamespaceView(this, e -> true);
		else
			return new NamespaceView(this, e -> namespace.equals(e.getNamespace()));
	}

	class NamespaceView  implements IModuleView {
		private final IModule module;
		private Predicate<INamespaceElement> filter;
		private List<ListenerFilter> listeners;

		NamespaceView(IModule module, Predicate<INamespaceElement> filter) {
			this.module = module;
			this.filter = filter;
			this.listeners = new ArrayList<>();
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

		@Override
		public void addListener(IListener listener) {
			ListenerFilter l = new ListenerFilter(listener);
			listeners.add(l);
			module.addListener(l);
		}

		@Override
		public void removeListener(IListener listener) {
			ListenerFilter r = null;
			for(ListenerFilter l : listeners)
				if(l.listener == listener)
					r = l;

			if(r != null) {
				module.removeListener(r);
				listeners.remove(r);
			}
		}
		
		@Override
		public void setProperty(Object key, Object value) {
			module.setProperty(key, value);
		}

		@Override
		public Object getProperty(Object key) {
			return module.getProperty(key);
		}

		@Override
		public void cloneProperties(IProgramElement e) {
			module.cloneProperties(e);
		}

		@Override
		public String toString() {
			return Translator.INSTANCE.translate(this);
		}
		
		class ListenerFilter implements IListener {

			final IListener listener;

			public ListenerFilter(IListener listener) {
				this.listener = listener;
			}

			@Override
			public void commandExecuted(ICommand<?> command) {
				listener.commandExecuted(command);
			}

			@Override
			public void constantAdded(IConstantDeclaration constant) {
				if(filter.test(constant))
					listener.constantRemoved(constant);
			}

			@Override
			public void constantRemoved(IConstantDeclaration constant) {
				if(filter.test(constant))
					listener.constantRemoved(constant);
			}

			@Override
			public void procedureAdded(IProcedure procedure) {
				if(filter.test(procedure))
					listener.procedureAdded(procedure);
			}

			@Override
			public void procedureRemoved(IProcedure procedure) {
				if(filter.test(procedure))
					listener.procedureRemoved(procedure);
			}

			@Override
			public void recordTypeAdded(IRecordType type) {
				if(filter.test(type))
					listener.recordTypeAdded(type);
			}
		}


		

	}
}
