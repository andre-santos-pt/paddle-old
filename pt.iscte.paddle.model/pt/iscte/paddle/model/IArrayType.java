package pt.iscte.paddle.model;

import java.util.Arrays;
import java.util.List;

import pt.iscte.paddle.model.impl.Literal;

public interface IArrayType extends IType {
	int getDimensions();
	IType getComponentType();
	IType getComponentTypeAt(int dim);
	
	default IType getRootComponentType() {
		return getComponentTypeAt(getDimensions());
	}
	
	default IExpression getDefaultExpression() {
		return Literal.NULL;
	}
	
	default boolean isSame(IProgramElement e) {
		return e instanceof IArrayType && 
				getComponentType().isSame(((IArrayType) e).getComponentType()) &&
				getDimensions() == ((IArrayType) e).getDimensions();
	}
	
	
	
	IArrayAllocation heapAllocation(List<IExpression> dimensions);
	
	default IArrayAllocation heapAllocation(IExpression ... dimensions) {
		return heapAllocation(Arrays.asList(dimensions));
	}
	
	default IArrayAllocation heapAllocation(IExpressionView ... views) {
		return heapAllocation(IExpressionView.toList(views));
	}
	
	
	
	IArrayAllocation heapAllocationWith(List<IExpression> elements);
	
	default IArrayAllocation heapAllocationWith(IExpression ... elements) {
		return heapAllocationWith(Arrays.asList(elements));
	}

}
