package pt.iscte.paddle.model;

import java.util.List;

public interface ICompositeExpression extends IExpression {
	default int getNumberOfParts() {
		return getParts().size();
	}

	List<IExpression> getParts();
}
