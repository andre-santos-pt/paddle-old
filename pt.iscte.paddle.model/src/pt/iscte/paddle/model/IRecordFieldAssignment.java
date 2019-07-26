package pt.iscte.paddle.model;

import java.util.Arrays;
import java.util.List;

public interface IRecordFieldAssignment extends IStatement {

	IRecordFieldExpression getTarget();
	IVariable getField();
	IExpression getExpression();

	@Override
	default List<IExpression> getExpressionParts() {
		return Arrays.asList(getExpression());
	}
}

