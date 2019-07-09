package pt.iscte.paddle.model;

import java.util.List;

import pt.iscte.paddle.model.IBlock.IVisitor;

/**
 * Mutable
 */
public interface IProcedure extends IProcedureDeclaration {
	List<IVariable> getLocalVariables();
	List<IVariable> getVariables();
	IType getReturnType();
	
	IBlock getBody();
	
	default boolean isRecursive() {
		IVisitor v = new IVisitor() {
			@Override
			public boolean visit(IProcedureCall call) {
				return IVisitor.super.visit(call);
			}
		};
		getBody().accept(v);
		return false;
	}
	
	default void accept(IBlock.IVisitor visitor) {
		getBody().accept(visitor);
	}
	
	default String translate(IModel2CodeTranslator t) {
		return t.header(this) + t.statements(getBody()) + t.close(this);
	}
}
