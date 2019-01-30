package pt.iscte.paddle.asg;

public interface ISimpleExpression extends IExpression {

	@Override
	default int getNumberOfParts() {
		return 0;
	}
}
