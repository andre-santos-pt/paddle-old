package pt.iscte.paddle.model.impl;

import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Iterables;

import pt.iscte.paddle.model.IConstant;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.IModel2CodeTranslator;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProcedureDeclaration;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.model.commands.IAddCommand;
import pt.iscte.paddle.model.commands.ICommand;
import pt.iscte.paddle.model.validation.AsgSemanticChecks;
import pt.iscte.paddle.model.validation.ISemanticProblem;
import pt.iscte.paddle.model.validation.SemanticChecker;

public class Module extends ListenableProgramElement<IModule.IListener> implements IModule {
	private final List<IConstant> constants;
	private final List<IRecordType> records;
	private final List<IProcedure> procedures;

	private final List<IProcedure> builtinProcedures;

	private final History history;
	
	private class History {
		private final ArrayDeque<ICommand<?>> commands = new ArrayDeque<>();
		private final ArrayDeque<ICommand<?>> redo = new ArrayDeque<>();

		void executeCommand(ICommand<?> cmd) {
			cmd.execute();
			commands.push(cmd);
		}

		public void undo() {
			if(!commands.isEmpty()) {
				ICommand<?> cmd = commands.pop();
				System.out.println("UNDO " + cmd.toText());
				cmd.undo();
				redo.push(cmd);
			}
		}

		public void redo() {
			if(!redo.isEmpty())
				executeCommand(redo.pop());
		}
	}

	
	
	public Module(boolean recordHistory) {
		constants = new ArrayList<>();
		records = new ArrayList<>();
		procedures = new ArrayList<>();
		builtinProcedures = new ArrayList<>();
		history = recordHistory ? new History() : null;
	}

	public void loadBuildInProcedures(Class<?> staticClass) {
		for (Method method : staticClass.getDeclaredMethods()) {
			if(BuiltinProcedure.isValidForBuiltin(method))
				builtinProcedures.add(new BuiltinProcedure(this, method));
			else
				System.err.println("not valid for built-in procedure: " + method);
		}
	}
	
	void executeCommand(ICommand<?> cmd) {
		if(history == null)
			cmd.execute();
		else
			history.executeCommand(cmd);
//		System.out.println("CMD: " + cmd.toText());
//		getListeners().forEachRemaining(l -> l.commandExecuted(cmd));
	}
	
	public void undo() {
		if(history != null)
			history.undo();
	}
	
	public void redo() {
		if(history != null)
			history.redo();
	}

	@Override
	public Collection<IConstant> getConstants() {
		return Collections.unmodifiableList(constants);
	}

	@Override
	public Collection<IRecordType> getRecordTypes() {
		return Collections.unmodifiableList(records);
	}

	@Override
	public Collection<IProcedure> getProcedures() {
		return Collections.unmodifiableList(procedures);
	}

	@Override
	public IConstant addConstant(IType type, ILiteral value) {
		assert type != null;
		assert value != null;
		ConstantDeclaration dec = new ConstantDeclaration(this, type, value);
		constants.add(dec);
		return dec;
	}

	@Override
	public IRecordType addRecordType() {
		IRecordType struct = new RecordType();
		records.add(struct);
		return struct;
	}

	private class AddProcedure implements IAddCommand<IProcedure> {
		final IType returnType;
		IProcedure procedure;

		AddProcedure(IType returnType) {
			this.returnType = returnType;
		}

		@Override
		public void execute() {
			if(procedure == null)
				procedure = new Procedure(Module.this, returnType);
			procedures.add(procedure);
			getListeners().forEachRemaining(l -> l.procedureAdded(procedure));
		}

		@Override
		public void undo() {
			procedures.remove(procedure);
			getListeners().forEachRemaining(l -> l.procedureDeleted(procedure));
		}

		@Override
		public IProgramElement getParent() {
			return Module.this;
		}

		@Override
		public IProcedure getElement() {
			return procedure;
		}
	}


	@Override
	public IProcedure addProcedure(IType returnType) {
		AddProcedure proc = new AddProcedure(returnType);
		executeCommand(proc);
		return proc.getElement();
		//		IProcedure proc = new Procedure(returnType);
		//		procedures.add(proc);
		//		getListeners().forEachRemaining(l -> l.procedureAdded(proc));
		//		return proc;
	}

	@Override
	public IProcedure resolveProcedure(IProcedureDeclaration procedureDeclaration) {
		for(IProcedure p : Iterables.concat(procedures, builtinProcedures))
			if(p.hasSameSignature(procedureDeclaration))
				return p;

		return null;
	}

	@Override
	public IProcedure resolveProcedure(String id, IType... paramTypes) {
		for(IProcedure p : Iterables.concat(procedures, builtinProcedures))
			if(p.matchesSignature(id, paramTypes))
				return p;

		return null;
	}

	@Override
	public String toString() {
		String text = "";
		for(IConstant c : constants)
			text += c.getType() + " " + c.getId() + " = " + c.getValue() + ";\n";

		if(!constants.isEmpty())
			text += "\n";

		for(IRecordType r : records) {
			text += "typedef struct\n{\n";
			for (IVariable member : r.getFields()) {
				text += "\t" + member.getDeclaration() + ";\n";
			}
			text += "} " + r.getId() + ";\n\n";
		}

		for (IProcedure p : builtinProcedures)
			System.out.println(p.longSignature() + "\t(built-in)\n");

		text += "\n";

		for (IProcedure p : procedures)
			text += p + "\n\n";

		return text;
	}

	@Override
	public List<ISemanticProblem> checkSemantics() {
		SemanticChecker checker = new SemanticChecker(new AsgSemanticChecks());
		return checker.check(this);
	}

	@Override
	public String translate(IModel2CodeTranslator t) {
		String text = t.header(this);
		for(IConstant c : constants)
			text += t.declaration(c);

		for(IRecordType r : records)
			text += t.declaration(r);

		for (IProcedure p : builtinProcedures)
			System.out.println(p.longSignature() + "\t(built-in)\n");

		//		text += "\n";

		for (IProcedure p : procedures)
			text += p.translate(t);

		return text + t.close(this);
	}

	// for tests only
	public void addProcedure(IProcedure p) {
		procedures.add(p);
	}
}
