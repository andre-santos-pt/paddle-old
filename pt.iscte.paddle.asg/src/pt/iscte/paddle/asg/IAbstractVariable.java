package pt.iscte.paddle.asg;

import java.util.Arrays;
import java.util.List;

public interface IAbstractVariable {

	IVariableAddress address();

	IVariableReferenceValue valueOf();


	IArrayLengthExpression arrayLength(List<IExpression> indexes);
	default IArrayLengthExpression arrayLength(IExpression ... indexes) {
		return arrayLength(Arrays.asList(indexes));
	}

	IArrayElementExpression arrayElement(List<IExpression> indexes);
	default IArrayElementExpression arrayElement(IExpression ... indexes) {
		return arrayElement(Arrays.asList(indexes));
	}


	IRecordFieldExpression member(String memberId);
}
