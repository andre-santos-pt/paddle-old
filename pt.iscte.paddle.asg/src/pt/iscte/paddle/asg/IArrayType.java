package pt.iscte.paddle.asg;

import java.util.Arrays;
import java.util.List;

public interface IArrayType extends IDataType {
	int getDimensions();
	IDataType getComponentType();
	IDataType getComponentTypeAt(int dim);
	
	IArrayAllocation allocation(List<IExpression> dimensions);
	
	default IArrayAllocation allocation(IExpression ... dimensions) {
		return allocation(Arrays.asList(dimensions));
	}
}
