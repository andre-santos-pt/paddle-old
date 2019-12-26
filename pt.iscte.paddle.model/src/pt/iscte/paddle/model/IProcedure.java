package pt.iscte.paddle.model;

import java.util.Collections;
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
		public List<IVariable> getParameters() {
			return null;
		}

		@Override
		public IVariable addParameter(IType type) {
			return null;
		}

		@Override
		public IProcedureCall call(List<IExpression> args) {
			return null;
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
		public List<IVariable> getLocalVariables() {
			return Collections.emptyList();
		}

		@Override
		public List<IVariable> getVariables() {
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
