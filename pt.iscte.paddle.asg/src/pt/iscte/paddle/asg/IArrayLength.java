package pt.iscte.paddle.asg;

import java.util.List;

public interface IArrayLength extends ICompositeExpression {
	IVariable getVariable();
	List<IExpression> getIndexes(); // size() >= 1
}
