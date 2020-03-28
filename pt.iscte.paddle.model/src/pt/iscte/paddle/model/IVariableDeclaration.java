package pt.iscte.paddle.model;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.model.impl.RecordFieldExpression;
import pt.iscte.paddle.model.impl.VariableExpression;

public interface IVariableDeclaration extends IInnocuousStatement, IExpressionView {
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
	
	default boolean isUnbound() {
		return this instanceof UnboundVariable;
	}

	default int procedureIndex() {
		return getOwnerProcedure().getVariables().indexOf(this);
	}
	
	default String getDeclaration() {
		return getType() + " " + getId();
	}
	
	IVariableExpression expression();
	
	IVariableAddress address();

	IVariableDereference dereference();


	IArrayLength length(List<IExpression> indexes);
	default IArrayLength length(IExpression ... indexes) {
		return length(Arrays.asList(indexes));
	}
	default IArrayLength length(IExpressionView ... views) {
		return length(IExpressionView.toList(views));
	}
	
	IArrayElement element(List<IExpression> indexes);
	default IArrayElement element(IExpression ... indexes) {
		return element(Arrays.asList(indexes));
	}
	default IArrayElement element(IExpressionView ... views) {
		return element(IExpressionView.toList(views));
	}
	
	
	IRecordFieldExpression field(IVariableDeclaration field);
	
	default IRecordFieldExpression field(String id) {
		return field(new UnboundVariable(id));
	}
	
	class UnboundVariable implements IVariableDeclaration {
		
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
			return null;
		}
		
		@Override
		public IArrayLength length(List<IExpression> indexes) {
			return null;
		}
		
		@Override
		public IType getType() {
			return type == null ? IType.UNBOUND : type;
		}
		
		@Override
		public IProgramElement getParent() {
			return null;
		}
		
		@Override
		public IRecordFieldExpression field(IVariableDeclaration field) {
			return new RecordFieldExpression(this.expression(), field);
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

		@Override
		public IVariableExpression expression() {
			return new VariableExpression(this);
		}
	};
}
