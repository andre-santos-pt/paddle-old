package pt.iscte.paddle.model;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;

public interface IVariable extends ISimpleExpression, IStatement {
	IProgramElement getParent();
	IType getType();

	default boolean isRecordField() {
		return getParent() instanceof IRecordType;
	}

	default boolean isLocalVariable() {
		return getParent() instanceof IBlock;
	}

	@Override
	default List<IExpression> getExpressionParts() {
		return ImmutableList.of();
	}
	
	IVariableAddress address();

	IVariableDereference dereference();

	default int procedureIndex() {
		return getOwnerProcedure().getVariables().indexOf(this);
	}


	IArrayLength length(List<IExpression> indexes);
	default IArrayLength length(IExpression ... indexes) {
		return length(Arrays.asList(indexes));
	}

	IArrayElement element(List<IExpression> indexes);
	default IArrayElement element(IExpression ... indexes) {
		return element(Arrays.asList(indexes));
	}

	IRecordFieldExpression field(IVariable field);
	
	default String getDeclaration() {
		return getType() + " " + getId();
	}
	
	class UnboundVariable implements IVariable {
		
		final String id;
		final IType type;
		
		public UnboundVariable(String id) {
			this(null, id);
		}
		
		public UnboundVariable(IType t, String id) {
			this.id = id;
			this.type = t;
		}
		
		@Override
		public String getId() {
			return id;
		}

		@Override
		public void setProperty(Object key, Object value) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public Object getProperty(Object key) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IConditionalExpression conditional(IExpression trueCase, IExpression falseCase) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IArrayLength length(List<IExpression> indexes) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IType getType() {
			return type == null ? IType.UNBOUND : type;
		}
		
		@Override
		public IProgramElement getParent() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IRecordFieldExpression field(IVariable field) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IArrayElement element(List<IExpression> indexes) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IVariableDereference dereference() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IVariableAddress address() {
			// TODO Auto-generated method stub
			return null;
		}
	};
}
