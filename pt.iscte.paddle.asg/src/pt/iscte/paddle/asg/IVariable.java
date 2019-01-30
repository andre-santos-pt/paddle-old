package pt.iscte.paddle.asg;

import java.util.Arrays;
import java.util.List;

public interface IVariable extends IIdentifiableElement, ISimpleExpression, IAbstractVariable {
	IProgramElement getParent();
	IDataType getType();

	default boolean isStructField() {
		return getParent() instanceof IStructType;
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
	
//	default boolean isReference() {
//		return getType() instanceof IReferenceType;
//	}
	
//	boolean isPointer();
	
//	IVariableAssignment addTargetAssignment(IExpression exp);
//	IVariableExpression expression();

	
//	IVariableAssignment addAssignment(IExpression exp);
//	
//	IStructMemberAssignment addMemberAssignment(String memberId, IExpression expression);
//	
//	IArrayElementAssignment addArrayAssignment(IExpression expression, List<IExpression> indexes);
//	default IArrayElementAssignment addArrayAssignment(IExpression expression, IExpression ... indexes) {
//		return addArrayAssignment(expression, Arrays.asList(indexes));
//	}

	
//	IVariableAddress address();
//	
//	IVariableReferenceValue valueOf();
//	
//
//	IArrayLengthExpression arrayLength(List<IExpression> indexes);
//	default IArrayLengthExpression arrayLength(IExpression ... indexes) {
//		return arrayLength(Arrays.asList(indexes));
//	}
//	
//	IArrayElementExpression arrayElement(List<IExpression> indexes);
//	default IArrayElementExpression arrayElement(IExpression ... indexes) {
//		return arrayElement(Arrays.asList(indexes));
//	}
//	
//
//	IStructMemberExpression member(String memberId);
}
