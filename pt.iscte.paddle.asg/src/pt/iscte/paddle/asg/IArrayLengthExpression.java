package pt.iscte.paddle.asg;

import java.util.List;

public interface IArrayLengthExpression extends ICompositeExpression {
	IVariable getVariable();
	List<IExpression> getIndexes(); // size() >= 1
}
