package pt.iscte.paddle.model;

import java.util.List;

public interface IArrayAccess extends IProgramElement {
	ITargetExpression getTarget();
	List<IExpression> getIndexes(); // not null, length > 0 && length <= getDimensions
}
