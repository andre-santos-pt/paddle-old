package pt.iscte.paddle.asg.impl;

import java.util.ArrayList;
import java.util.List;

import pt.iscte.paddle.asg.IArrayAllocation;
import pt.iscte.paddle.asg.IArrayType;
import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.ILiteral;
import pt.iscte.paddle.asg.IReferenceType;

public class ArrayType extends ProgramElement implements IArrayType {
	private final IType componentType;
	private final int dimensions;
	private final String id;

	public ArrayType(IType type) {
		this.componentType = type;
		this.dimensions = getDimensionsInternal();
		this.id = componentType.getId() + "[]";
	}
	
	@Override
	public int getDimensions() {
		return dimensions;
	}
	
	private int getDimensionsInternal() {
		if(!(componentType instanceof ArrayType))
			return 1;
		else
			return 1 + ((ArrayType) componentType).getDimensionsInternal();
	}

	@Override
	public String getId() {
		return id;
	}

	public boolean sameAs(IArrayType type) {
		return id.equals(type.getId());
	}

	@Override
	public String toString() {
		return id;
	}

	@Override
	public Object getDefaultValue() {
		return null;
	}

	@Override
	public IType getComponentType() {
		return componentType;
	}
	
	@Override
	public IType getComponentTypeAt(int dim) {
		assert dim >= 1 && dim <= dimensions;
		if(dim == 1)
			return componentType;
		else
			return ((IArrayType) componentType).getComponentTypeAt(dim-1);		
	}

	@Override
	public int getMemoryBytes() {
		return 8;
	}
	
	@Override
	public IReferenceType reference() {
		return new ReferenceType(this);
	}
	
	@Override
	public IArrayAllocation allocation(List<IExpression> dimExpressions) {
//		List<IExpression> dims = new ArrayList<IExpression>(dimExpressions);
//		for(int i = 0; i < getDimensionsInternal() - dimExpressions.size(); i++)
//			dims.add(ILiteral.literal(-1));
		return new ArrayAllocation(this, dimExpressions);
	}
}