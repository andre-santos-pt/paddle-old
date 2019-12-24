package pt.iscte.paddle.model;

import java.util.List;

import pt.iscte.paddle.model.IBlock.IVisitor;

/**
 * Mutable
 */
public interface IProcedure extends IProcedureDeclaration {
	IModule getModule();
	List<IVariable> getLocalVariables();
	List<IVariable> getVariables();
	IType getReturnType();
	
	IBlock getBody();
	
	default boolean isRecursive() {
		class RecFind implements IVisitor {
			boolean foundRecursiveCall = false;

			public boolean visit(IProcedureCall call) {
				if(call.getProcedure().equals(IProcedure.this))
					foundRecursiveCall = true;
				return true;
			}
		};
		RecFind r = new RecFind();
		getBody().accept(r);
		return r.foundRecursiveCall;
	}
	
	default IVariable getVariable(String id) {
		for(IVariable v : getVariables())
			if(id.equals(v.getId()))
				return v;
		return null;
	}
	
//	default boolean isFunction() {
//		if(getReturnType().isVoid())
//			return false;
//	}
//	
//	default boolean isStateLessFunction() {
//		if(getReturnType().isVoid())
//			return false;
//	}
	
	default void accept(IBlock.IVisitor visitor) {
		getBody().accept(visitor);
	}
	
	default String translate(IModel2CodeTranslator t) {
		return t.header(this) + t.statements(getBody()) + t.close(this);
	}
}
