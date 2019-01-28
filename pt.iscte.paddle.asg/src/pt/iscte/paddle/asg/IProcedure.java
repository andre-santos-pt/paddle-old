package pt.iscte.paddle.asg;

import java.util.List;

import pt.iscte.paddle.asg.IBlock.IVisitor;

/**
 * Mutable
 */
public interface IProcedure extends IProcedureDeclaration {
//	IModule getModule();
	List<IVariable> getLocalVariables();
	List<IVariable> getVariables();
	IVariable getVariable(String id);
	IDataType getReturnType();
	
	IBlock getBody();
	
	default boolean isRecursive() {
		IVisitor v = new IVisitor() {
			@Override
			public boolean visit(IProcedureCall call) {
				return IVisitor.super.visit(call);
			}
			@Override
			public boolean visit(IProcedureCallExpression exp) {
				// TODO Auto-generated method stub
				return IVisitor.super.visit(exp);
			}
		};
		getBody().accept(v);
		return false;
	}
	
	
	
}
