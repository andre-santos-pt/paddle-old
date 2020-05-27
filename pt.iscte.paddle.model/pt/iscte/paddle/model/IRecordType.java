package pt.iscte.paddle.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import pt.iscte.paddle.model.impl.Literal;
import pt.iscte.paddle.model.impl.ProgramElement;
import pt.iscte.paddle.model.impl.RecordAllocation;
import pt.iscte.paddle.model.impl.ReferenceType;

/**
 * Mutable
 */
public interface IRecordType extends IType, Iterable<IVariableDeclaration>, IListenable<IRecordType.IListener> {
	
	interface IListener {
		default void fieldAdded(IVariableDeclaration field) { }
		default void fieldRemoved(IVariableDeclaration field) { }
	}
	
	IModule getModule();
	
	List<IVariableDeclaration> getFields();
	
	default IVariableDeclaration getField(String id) {
		for(IVariableDeclaration f : getFields())
			if(id.equals(f.getId()))
				return f;
		
		return null;
	}
	
	IVariableDeclaration addField(IType type);

	IVariableDeclaration addField(IType type, String id, List<String> flags);
	
	default IVariableDeclaration addField(IType type, String id, String ... flags) {
		return addField(type, id, Arrays.asList(flags));
	}
	
	void removeField(IVariableDeclaration var);
	
	@Override
	default Object getDefaultValue() {
		return null;
	}

	IRecordAllocation heapAllocation();
	
//	TODO IRecordAllocation stackAllocation();

	default IExpression getDefaultExpression() {
		return Literal.NULL;
	}
	
	default Iterator<IVariableDeclaration> iterator() {
		return getFields().iterator();
	}

	class UnboundRecordType extends ProgramElement implements IRecordType {
		public UnboundRecordType(String id) {
			setId(id);
		}
		@Override
		public int getMemoryBytes() {
			return 4;
		}

		@Override
		public IReferenceType reference() {
			return new ReferenceType(this);
		}

		@Override
		public String toString() {
			return getId();
		}
		
		@Override
		public IModule getModule() {
			return null;
		}

		@Override
		public List<IVariableDeclaration> getFields() {
			return List.of();
		}

		@Override
		public IVariableDeclaration addField(IType type) {
			throw new UnsupportedOperationException();
		}

		@Override
		public IVariableDeclaration addField(IType type, String id, List<String> flags) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void removeField(IVariableDeclaration var) {
			throw new UnsupportedOperationException();
		}

		@Override
		public IRecordAllocation heapAllocation() {
			return new RecordAllocation(this);
		}
		@Override
		public void addListener(IListener listener) {
			throw new UnsupportedOperationException();
			
		}
		@Override
		public void removeListener(IListener listener) {
			throw new UnsupportedOperationException();
			
		}
		
	}


}
