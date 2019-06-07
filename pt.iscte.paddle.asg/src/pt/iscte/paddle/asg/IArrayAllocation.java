package pt.iscte.paddle.asg;

import java.util.List;

public interface IArrayAllocation extends ICompositeExpression {
//	IArrayType getArrayType();
	List<IExpression> getDimensions();
	
	boolean onHeapMemory();
}
