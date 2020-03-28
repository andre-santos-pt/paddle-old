package pt.iscte.paddle.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import pt.iscte.paddle.model.impl.Literal;

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

	
}
