package pt.iscte.paddle.asg;

import java.util.List;

public interface IArrayAllocation extends IExpression {
	int getNumberOfDimensions();
	List<IExpression> getDimensions();
}
