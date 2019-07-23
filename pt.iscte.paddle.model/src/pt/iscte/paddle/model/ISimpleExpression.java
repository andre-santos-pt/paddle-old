package pt.iscte.paddle.model;

public interface ISimpleExpression extends IExpression {

	@Override
	default int getNumberOfParts() {
		return 0;
	}
}
