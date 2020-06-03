package pt.iscte.paddle.model;

import java.util.Collections;
import java.util.List;

import pt.iscte.paddle.model.IBlock.IVisitor;
import pt.iscte.paddle.model.cfg.IBranchNode;
import pt.iscte.paddle.model.cfg.IControlFlowGraph;
import pt.iscte.paddle.model.cfg.impl.Visitor;
import pt.iscte.paddle.model.impl.BuiltinProcedureReflective;
import pt.iscte.paddle.model.impl.ProcedureCall;
import pt.iscte.paddle.model.impl.UnboundProcedure;

/**
 * Mutable
 */
public interface IProcedure extends IProcedureDeclaration {
	IModule getModule();
	List<IVariableDeclaration> getLocalVariables();
	List<IVariableDeclaration> getVariables();
	IType getReturnType();

	IBlock getBody();

	
	default IControlFlowGraph generateCFG(){
		IControlFlowGraph cfg = IControlFlowGraph.create(this);
		IVisitor visitor = new Visitor(cfg);

		this.accept(visitor);
		
		// When cfg is empty.
		if(cfg.getNodes().size() == 2) cfg.getEntryNode().setNext(cfg.getExitNode());
		if(getReturnType() == IType.VOID)
			cfg.getNodes().forEach(node -> {
				
				if(node.getNext() == null && !node.isExit()) node.setNext(cfg.getExitNode());
				if(node instanceof IBranchNode && !((IBranchNode) node).hasBranch() && !node.isExit()) ((IBranchNode) node).setBranch(cfg.getExitNode());
			});

		return cfg;
	}

	default boolean isRecursive() {
		class RecFind implements IVisitor {
			boolean foundRecursiveCall = false;

			public boolean visit(IProcedureCall call) {
				if(call.getProcedure().equals(IProcedure.this))
					foundRecursiveCall = true;
				return true;
			}
			
			@Override
			public boolean visit(IProcedureCallExpression exp) {
				if(exp.getProcedure().equals(IProcedure.this))
					foundRecursiveCall = true;
				return true;
			}
		};
		RecFind r = new RecFind();
		getBody().accept(r);
		return r.foundRecursiveCall;
	}

	default IVariableDeclaration getVariable(String id) {
		for(IVariableDeclaration v : getVariables())
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

	static IProcedure createUnbound(String namespace, String id) {
		return new UnboundProcedure(id, namespace);
	}

	static IProcedure createUnbound(String id) {
		return new UnboundProcedure(id, null);
	}
}
