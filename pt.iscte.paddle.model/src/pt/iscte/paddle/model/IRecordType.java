package pt.iscte.paddle.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import pt.iscte.paddle.model.impl.Literal;
import pt.iscte.paddle.model.impl.RecordAllocation;

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

	class UnboundRecordType implements IRecordType {
		final String id;
		public UnboundRecordType(String id) {
			this.id = id;
		}
		@Override
		public int getMemoryBytes() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public IReferenceType reference() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setProperty(Object key, Object value) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getId() {
			return id;
		}
		
		@Override
		public String toString() {
			return id;
		}
		
		@Override
		public Object getProperty(Object key) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void addListener(IListener listener) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeListener(IListener listener) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public IModule getModule() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<IVariableDeclaration> getFields() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IVariableDeclaration addField(IType type) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IVariableDeclaration addField(IType type, String id, List<String> flags) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void removeField(IVariableDeclaration var) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public IRecordAllocation heapAllocation() {
			return new RecordAllocation(this);
		}
		
	}
}
