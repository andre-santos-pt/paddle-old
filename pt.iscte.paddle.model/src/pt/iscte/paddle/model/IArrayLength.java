package pt.iscte.paddle.model;

import java.util.List;

public interface IArrayLength extends ICompositeExpression {
	IExpression getTarget();
	List<IExpression> getIndexes(); // size() >= 1
}
