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
	
	default IExpression getDefaultExpression() {
		return ILiteral.NULL;
	}
	
	default boolean isSame(IProgramElement e) {
		return e instanceof IArrayType && 
				getComponentType().isSame(((IArrayType) e).getComponentType()) &&
				getDimensions() == ((IArrayType) e).getDimensions();
	}
	
	
	IArrayAllocation stackAllocation(List<IExpression> dimensions);
	
	default IArrayAllocation stackAllocation(IExpression ... dimensions) {
		return stackAllocation(Arrays.asList(dimensions));
	}
	default IArrayAllocation stackAllocation(IExpressionView ... views) {
		return stackAllocation(IExpressionView.toList(views));
	}
	
	IArrayAllocation heapAllocation(List<IExpression> dimensions);
	
	default IArrayAllocation heapAllocation(IExpression ... dimensions) {
		return heapAllocation(Arrays.asList(dimensions));
	}
	default IArrayAllocation heapAllocation(IExpressionView ... views) {
		return heapAllocation(IExpressionView.toList(views));
	}
	

}
