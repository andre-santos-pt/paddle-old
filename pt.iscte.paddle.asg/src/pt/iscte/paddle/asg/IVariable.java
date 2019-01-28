package pt.iscte.paddle.asg;

import java.util.Arrays;
import java.util.List;

public interface IVariable extends IIdentifiableElement {
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
	
	boolean isPointer();
	
	IVariableExpression expression();
	
	IVariableAddress expressionAddress();
	
	IVariableReferenceValue referenceValue();
	
	IStructMemberExpression memberExpression(String memberId);

	IVariableAssignment addAssignment(IExpression exp);
	
	IVariableAssignment addTargetAssignment(IExpression exp);

	IStructMemberAssignment addMemberAssignment(String memberId, IExpression expression);
	
	IArrayLengthExpression lengthExpression(List<IExpression> indexes);
	default IArrayLengthExpression lengthExpression(IExpression ... indexes) {
		return lengthExpression(Arrays.asList(indexes));
	}
	
	IArrayElementExpression elementExpression(List<IExpression> indexes);
	default IArrayElementExpression elementExpression(IExpression ... indexes) {
		return elementExpression(Arrays.asList(indexes));
	}
	
	IArrayElementAssignment elementAssignment(IExpression expression, List<IExpression> indexes);
	default IArrayElementAssignment elementAssignment(IExpression expression, IExpression ... indexes) {
		return elementAssignment(expression, Arrays.asList(indexes));
	}
}
