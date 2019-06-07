package pt.iscte.paddle.asg;

import java.util.Arrays;
import java.util.List;

public interface IArrayType extends IType {
	int getDimensions();
	IType getComponentType();
	IType getComponentTypeAt(int dim);
	
	IArrayAllocation stackAllocation(List<IExpression> dimensions);
	
	default IArrayAllocation stackAllocation(IExpression ... dimensions) {
		return stackAllocation(Arrays.asList(dimensions));
	}
	
	IArrayAllocation heapAllocation(List<IExpression> dimensions);
	
	default IArrayAllocation heapAllocation(IExpression ... dimensions) {
		return heapAllocation(Arrays.asList(dimensions));
	}
}
