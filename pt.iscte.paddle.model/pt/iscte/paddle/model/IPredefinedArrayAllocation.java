package pt.iscte.paddle.model;

import java.util.List;

public interface IPredefinedArrayAllocation extends IArrayAllocation {

	List<IExpression> getElements();
	
	@Override
	default List<IExpression> getDimensions() {
		return List.of(IType.INT.literal(getElements().size()));
	}
}
