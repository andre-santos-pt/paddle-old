package pt.iscte.paddle.model;

import java.util.List;

public interface IArrayLength extends ICompositeExpression {
	IVariable getVariable();
	List<IExpression> getIndexes(); // size() >= 1
}
