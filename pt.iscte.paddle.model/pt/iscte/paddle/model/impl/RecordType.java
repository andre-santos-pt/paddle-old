package pt.iscte.paddle.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IRecordAllocation;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IReferenceType;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.commands.IAddCommand;
import pt.iscte.paddle.model.commands.IDeleteCommand;

class RecordType extends ListenableProgramElement<IRecordType.IListener> implements IRecordType {
	private final Module module;
	private final List<IVariableDeclaration> variables;
	
	RecordType(Module module) {
		this.module = module;
		this.variables = new ArrayList<>(5);
	}
	
	@Override
	public String toString() {
		return getId();
	}

	@Override
	public IModule getModule() {
		return module;
	}
	
	@Override
	public List<IVariableDeclaration> getFields() {
		return Collections.unmodifiableList(variables);
	}
	
	private class AddField implements IAddCommand<IVariableDeclaration> {
		private IVariableDeclaration field;
		
		AddField(IType type, String id, List<String> flags) {
			field = new VariableDeclaration(RecordType.this, type);
			field.setId(id);
			for(String f : flags)
				field.setFlag(f);
		}
		
		@Override
		public void execute() {
			variables.add(field);
			getListeners().forEachRemaining(l -> l.fieldAdded(field));
		}

		@Override
		public void undo() {
			variables.remove(field);
		}

		@Override
		public IVariableDeclaration getElement() {
			return field;
		}

		@Override
		public IProgramElement getParent() {
			return RecordType.this;
		}
	}
	
	@Override
	public IVariableDeclaration addField(IType type) {
		return addField(type, null);
	}
	
	@Override
	public IVariableDeclaration addField(IType type, String id, List<String> flags) {
		AddField add = new AddField(type, id, flags);
		module.executeCommand(add);
		return add.getElement();
	}
	
	private class RemoveField implements IDeleteCommand<IVariableDeclaration> {
		private IVariableDeclaration field;
		
		RemoveField(IVariableDeclaration field) {
			this.field = field;
		}
		
		@Override
		public void execute() {
			assert variables.contains(field);
			variables.remove(field);
			getListeners().forEachRemaining(l -> l.fieldRemoved(field));
		}

		@Override
		public void undo() {
			variables.add(field);
			getListeners().forEachRemaining(l -> l.fieldAdded(field));
		}

		@Override
		public IVariableDeclaration getElement() {
			return field;
		}
	}
	
	@Override
	public void removeField(IVariableDeclaration var) {
		RemoveField cmd = new RemoveField(var);
		module.executeCommand(cmd);
	}
	
	@Override
	public int getMemoryBytes() {
		int bytes = 0;
		for(IVariableDeclaration v : variables)
			bytes += v.getType().getMemoryBytes();
		return bytes;
	}
	
	@Override
	public IRecordAllocation heapAllocation() {
		return new RecordAllocation(this);
	}
	
	@Override
	public IReferenceType reference() {
		return new ReferenceType(this);
	}
}
