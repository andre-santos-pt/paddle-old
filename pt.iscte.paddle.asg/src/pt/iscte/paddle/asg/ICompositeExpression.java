package pt.iscte.paddle.asg;

import java.util.List;

public interface ICompositeExpression extends IExpression {
	boolean isDecomposable();

	default int getNumberOfParts() {
		return decompose().size();
	}

	List<IExpression> decompose();

}
