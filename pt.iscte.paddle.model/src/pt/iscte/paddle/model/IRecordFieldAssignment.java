package pt.iscte.paddle.model;

import java.util.Arrays;
import java.util.List;

public interface IRecordFieldAssignment extends IStatement {

	IRecordFieldExpression getTarget(); // TODO to normal expression as in arrays?
	IVariableDeclaration getField();
	IExpression getExpression();

	@Override
	default List<IExpression> getExpressionParts() {
		return Arrays.asList(getExpression());
	}
}

