package pt.iscte.paddle.model;

import java.util.Collections;
import java.util.List;

import pt.iscte.paddle.model.IBlock.IVisitor;
import pt.iscte.paddle.model.cfg.IBranchNode;
import pt.iscte.paddle.model.cfg.IControlFlowGraph;
import pt.iscte.paddle.model.cfg.impl.Visitor;
import pt.iscte.paddle.model.impl.ProcedureCall;

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

		if(this.getReturnType() == IType.VOID)
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

	default String translate(IModel2CodeTranslator t) {
		return t.header(this) + t.statements(getBody()) + t.close(this);
	}

	public class UnboundProcedure implements IProcedure {
		private String id;

		public UnboundProcedure(String id) {
			this.id = id;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public List<IVariableDeclaration> getParameters() {
			return null;
		}

		@Override
		public IVariableDeclaration addParameter(IType type) {
			return null;
		}

		@Override
		public IProcedureCallExpression expression(List<IExpression> args) {
			return new ProcedureCall(null, this, -1, args);
		}

		@Override
		public void setProperty(Object key, Object value) {
		}

		@Override
		public Object getProperty(Object key) {
			return null;
		}

		@Override
		public IModule getModule() {
			return null;
		}

		@Override
		public List<IVariableDeclaration> getLocalVariables() {
			return Collections.emptyList();
		}

		@Override
		public List<IVariableDeclaration> getVariables() {
			return Collections.emptyList();
		}

		@Override
		public IType getReturnType() {
			return IType.UNBOUND;
		}

		@Override
		public IBlock getBody() {
			return null;
		}

	}
}
