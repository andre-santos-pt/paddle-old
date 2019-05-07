package pt.iscte.paddle.asg;

import java.util.Arrays;
import java.util.List;

public interface IAbstractVariable {

	IVariableAddress address();

	IVariableReferenceValue valueOf();


	IArrayLength arrayLength(List<IExpression> indexes);
	default IArrayLength arrayLength(IExpression ... indexes) {
		return arrayLength(Arrays.asList(indexes));
	}

	IArrayElement arrayElement(List<IExpression> indexes);
	default IArrayElement arrayElement(IExpression ... indexes) {
		return arrayElement(Arrays.asList(indexes));
	}


	IRecordFieldExpression member(String memberId);
}
