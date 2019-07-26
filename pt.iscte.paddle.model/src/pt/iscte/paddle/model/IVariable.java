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

	default IProcedure getProcedure() {
		IProgramElement e = getParent();
		while(e != null && !(e instanceof IProcedure))
			e = ((IBlock) e).getParent();
		
		return e == null ? null : (IProcedure) e;
	}
	
	@Override
	default List<IExpression> getExpressionParts() {
		return ImmutableList.of();
	}
	
	IVariableAddress address();

	IVariableDereference dereference();

//	default IVariable resolve() {
//		return this;
//	}

	IArrayLength length(List<IExpression> indexes);
	default IArrayLength length(IExpression ... indexes) {
		return length(Arrays.asList(indexes));
	}

	IArrayElement element(List<IExpression> indexes);
	default IArrayElement element(IExpression ... indexes) {
		return element(Arrays.asList(indexes));
	}

	IRecordFieldExpression field(IVariable field);
	
//	IRecordFieldVariable fieldVariable(IVariable field);
	
	default String getDeclaration() {
		return getType() + " " + getId();
	}
}
