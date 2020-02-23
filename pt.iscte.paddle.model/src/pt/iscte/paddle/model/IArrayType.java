package pt.iscte.paddle.model;

import java.util.Arrays;
import java.util.List;

public interface IArrayType extends IType {
	int getDimensions();
	IType getComponentType();
	IType getComponentTypeAt(int dim);
	default IType getRootComponentType() {
		return getComponentTypeAt(getDimensions());
	}
	IArrayAllocation stackAllocation(List<IExpression> dimensions);
	
	default IArrayAllocation stackAllocation(IExpression ... dimensions) {
		return stackAllocation(Arrays.asList(dimensions));
	}
	
	IArrayAllocation heapAllocation(List<IExpression> dimensions);
	
	default IArrayAllocation heapAllocation(IExpression ... dimensions) {
		return heapAllocation(Arrays.asList(dimensions));
	}
}
