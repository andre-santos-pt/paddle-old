package pt.iscte.paddle.asg;

import java.util.List;

public interface IArrayLengthExpression extends IExpression {
	IVariable getVariable();
	List<IExpression> getIndexes(); // size() >= 1
}
