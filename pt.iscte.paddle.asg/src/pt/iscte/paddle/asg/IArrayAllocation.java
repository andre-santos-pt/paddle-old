package pt.iscte.paddle.asg;

import java.util.List;

public interface IArrayAllocation extends ICompositeExpression {
	default int getNumberOfDimensions() {
		return getDimensions().size();
	}
	IArrayType getArrayType();
	List<IExpression> getDimensions();
}
